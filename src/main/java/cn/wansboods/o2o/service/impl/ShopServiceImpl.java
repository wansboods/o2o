package cn.wansboods.o2o.service.impl;

import cn.wansboods.o2o.base.BaseService;
import cn.wansboods.o2o.dao.ShopDao;
import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.enums.ShopStateEmum;
import cn.wansboods.o2o.exceptions.ShopOperationException;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.service.ShopService;
import cn.wansboods.o2o.util.ImageUtil;
import cn.wansboods.o2o.util.PageCalculator;
import cn.wansboods.o2o.util.PathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

@Service
public class ShopServiceImpl extends BaseService<ShopDao> implements ShopService  {

    Logger logger = LoggerFactory.getLogger( ShopServiceImpl.class );

    public ShopExecution getShopList( Shop shopCondition, int pageIndex, int pageSize) {
        List<Shop> shopList = baseEntityMapper.queryShopList( shopCondition, PageCalculator.calculateRowIndex( pageIndex,pageSize ), pageSize );
        int count = baseEntityMapper.queryShopCount( shopCondition );
        ShopExecution se = new ShopExecution();
        logger.debug( "获取到店铺信息{}|总数{}", shopList.size(), count );
        if( null != shopList ){
            logger.debug( "------------1");
            se.setShopList( shopList );
            se.setCount( count );
            se.setState( ShopStateEmum.SUCCESS.getState() );
        }else{
            logger.debug( "------------2");
            se.setState( ShopStateEmum.INNER_ERROR.getState() );
        }
        return se;
    }

    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName ) throws ShopOperationException{

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
                if( shopImgInputStream != null ){
                    //存储图片
                    try {
                        addShopImg( shop, shopImgInputStream, fileName );
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



    private void addShopImg(Shop shop, InputStream shopImgInputStram, String fileName ) {
        // 获取 shop目录的相对路径
        String dest = PathUtil.getShopImagePath( shop.getShopId() );
        String shopImgAddr = ImageUtil.generateThumbnail( shopImgInputStram, fileName, dest );
        shop.setShopImg( shopImgAddr );
    }

    public Shop getByShopId(long shopId) {
        return baseEntityMapper.queryByShopId( shopId );
    }

    public ShopExecution modifyShop(Shop shop, InputStream shopInputStream, String fileName)
            throws ShopOperationException {
        if( shop == null || shop.getShopId() == null ){
            return new ShopExecution( ShopStateEmum.CHECK.NULL_SHOP );
        }

        try{
            //1.判断是否需要处理图片
            if( shopInputStream != null && fileName != null && !"".equals( fileName ) ){
                Shop tempShop = baseEntityMapper.queryByShopId( shop.getShopId() );
                if( tempShop.getShopImg() != null ){
                    ImageUtil.deleteFileOrPath( tempShop.getShopImg() );
                }

                addShopImg( shop, shopInputStream, fileName );
            }
            //2.更新店铺信息
            shop.setLastEditTime( new Date() );
            int effectedNum = baseEntityMapper.updateShop( shop );
            if( effectedNum <= 0 ){
                return new ShopExecution( ShopStateEmum.CHECK.INNER_ERROR );
            }else{
                shop = baseEntityMapper.queryByShopId( shop.getShopId() );
                return new ShopExecution( ShopStateEmum.CHECK.SUCCESS,shop );
            }
        }catch( Exception e ){
            throw  new ShopOperationException( "modifyShop error:" + e.getMessage() );
        }
    }

}
