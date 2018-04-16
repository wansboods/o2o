package cn.wansboods.o2o.service;

import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.exceptions.ShopOperationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.InputStream;


public interface ShopService {
    /**
     * 根据pagenum 和 pagesize 获取商铺信息
     * @param shopCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ShopExecution getShopList( Shop shopCondition, int pageIndex, int pageSize );

    /**
     * 增加店铺信息
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName );

    /**
     * 获取店铺信息
     * @param shopId
     * @return
     */
    Shop getByShopId( long shopId );

    /**
     * 更新店铺信息，包括图片处理
     * @param shop
     * @param inputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop( Shop shop, InputStream inputStream, String fileName ) throws ShopOperationException;
}
