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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

@Service
public class ShopServiceImpl extends BaseService<ShopDao> implements ShopService  {

    Logger logger = LoggerFactory.getLogger( ShopServiceImpl.class );

    @Transactional
    public ShopExecution addShop(Shop shop, File shopImg){

        //空值判断
        if( null == shop ){
            return new ShopExecution( ShopStateEmum.NULL_SHOPID );
        }

        try {
            //店铺信息赋初始值
            shop.setEnableStatus( 0 );
            shop.setCreatTime( new Date() );
            shop.setLastEditTime( new Date() );
            //添加店铺信息
            int effectedNum = baseEntityMapper.insertShop( shop );
            logger.debug( "=============> 插入的Shop id={}",effectedNum );
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
                        throw new ShopOperationException( "更新图片地址失败" );
                    }
//                    shop.getShopImg()
                }
            }
        }catch ( Exception e ){
            throw new ShopOperationException( "addShop err:" + e.getMessage() );
        }

        return new ShopExecution( ShopStateEmum.CHECK, shop );
    }


    private void addShopImg(Shop shop, File shopImg) {
        // 获取 shop目录的相对路径
        String dest = PathUtil.getShopImagePath( shop.getShopId() );
        String shopImgAddr = ImageUtil.generateThumbnail( shopImg, dest );
        shop.setShopImg( shopImgAddr );
    }
}
