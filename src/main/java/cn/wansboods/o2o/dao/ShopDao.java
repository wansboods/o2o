package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.model.Shop;

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop( Shop shop);
}
