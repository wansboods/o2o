package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.entity.Shop;

public interface ShopDao {

    /**
     * 通过shop id 查询店铺
     * @param shopId
     * @return
     */
    Shop queryByShopId( long shopId );

    /**
     * 新增店铺
     * @param shop
     * @return
     */
    int insertShop(Shop shop);

    /**
     *  更新店铺信息
     */

    int updateShop(Shop shop);
}
