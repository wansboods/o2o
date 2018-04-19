package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.entity.ProductCategory;

import java.util.List;

public interface productCategoryDao {

    /**
     * 获取商品分类信息
     * @param shopId
     * @return
     */
    List<ProductCategory> gueryProductCategoryList( Long shopId );


}
