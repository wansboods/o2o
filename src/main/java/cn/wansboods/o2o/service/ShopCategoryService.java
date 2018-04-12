package cn.wansboods.o2o.service;

import cn.wansboods.o2o.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
    List<ShopCategory> getShopCategoryList( ShopCategory shopCategoryCondition );
}
