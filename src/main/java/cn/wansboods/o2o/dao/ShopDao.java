package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopDao {

    /**
     * 查询总数店铺总数
     * @param shopCondition
     * @return
     */
    int queryShopCount( @Param("shopCondition") Shop shopCondition );

    /**
     * 分页查询店铺,可输入的条件有: 店铺名称(支持模糊),店铺状态,店铺类别，区域Id，owner
     * @param shopCondition  查询条件
     * @param rowIndex
     * @param pageSize
     * @return
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,@Param("rowIndex") int rowIndex,@Param("pageSize") int pageSize );


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
