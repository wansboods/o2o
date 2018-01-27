package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.entity.Area;
import cn.wansboods.o2o.entity.PersonInfo;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;

import static org.junit.Assert.assertEquals;

//import java.sql.Date;
public class ShopDaoTest extends BaseTest{
    @Autowired
    private ShopDao shopDao;
    @Test
    @Ignore
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owener = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owener.setUseId( 1L );
        area.setAreaId( 2 );
        shopCategory.setShopCategoryId(1L);
        shop.setOwner( owener );
        shop.setArea( area );
        shop.setShopCategory( shopCategory );
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        shop.setPhone("12345678901");
        shop.setShopImg( "test");
        shop.setCreatTime( new Date());
        shop.setEnableStatus( 1 );
        shop.setAdvice( "审核中");

        int effectedNum = shopDao.insertShop( shop );
        assertEquals( 1, effectedNum );
    }

    @Test
    public void testupdateShop() {
        Shop shop = new Shop();
        shop.setShopId( 2L );
        shop.setShopName("测试的店铺2");
        shop.setShopDesc("测试描述2");
        shop.setLastEditTime( new Date());
        int effectedNum = shopDao.updateShop( shop );
        assertEquals( 1, effectedNum );
    }
}
