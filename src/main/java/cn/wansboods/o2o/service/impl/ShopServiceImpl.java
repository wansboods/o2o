package cn.wansboods.o2o.service.impl;

import cn.wansboods.o2o.base.BaseService;
import cn.wansboods.o2o.dao.ShopDao;
import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.enums.ShopStateEmum;
import cn.wansboods.o2o.exceptions.ShopOperationException;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.service.ShopService;
import cn.wansboods.o2o.util.ImageUtil;
import cn.wansboods.o2o.util.PathUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

public class ShopServiceImpl extends BaseService<ShopDao> implements ShopService  {
    @Transactional
    public ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg) {
        if( null == shop ){
            return new ShopExecution( ShopStateEmum.NULL_SHOPID );
        }

        try {
            shop.setEnableStatus( 0 );
            shop.setCreatTime( new Date() );
            shop.setLastEditTime( new Date() );
            int effectedNum = baseEntityMapper.insertShop( shop );
            if( effectedNum <= 0 ){
                throw new ShopOperationException( "创建商铺信息失败" );
            }else {
                if( shopImg != null ){
                    //存储图片
                    try {
                        addShopImg( shop, shopImg );
                    }catch (Exception e ){
                        throw new ShopOperationException( "addShopImg err:" + e.getMessage() );
                    }

                    //更新店铺的图片的地址
                    effectedNum = baseEntityMapper.updateShop( shop );
                    if( effectedNum <= 0 ){
                        throw new ShopOperationException( "updateShop err" );
                    }
//                    shop.getShopImg()
                }
            }
        }catch ( Exception e ){
            throw new ShopOperationException( "addShop err:" + e.getMessage() );
        }

        return new ShopExecution( ShopStateEmum.CHECK );
    }

    private void addShopImg(Shop shop, CommonsMultipartFile shopImg) {
        // 获取shop目录的相对路径
        String dest = PathUtil.getShopImagePath( shop.getShopId() );
        String shopImgAddr = ImageUtil.generateThumbnail( shopImg, dest );
        shop.setShopImg( shopImgAddr );
    }
}
