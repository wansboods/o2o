package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.entity.Area;
import cn.wansboods.o2o.entity.PersonInfo;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.entity.ShopCategory;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;


import static org.junit.Assert.assertEquals;

//import java.sql.Date;
public class ShopDaoTest extends BaseTest{
    @Autowired
    private ShopDao shopDao;
    private Logger logger = LoggerFactory.getLogger( ShopDaoTest.class );
    @Test
    public void testQueryShopListAndCount(){
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId( 1L );
        shopCondition.setOwner( owner );

        List<Shop> shopList = shopDao.queryShopList( shopCondition, 0, 5 );
        logger.debug( "店铺列表大小:" + shopList.size() );
        int count = shopDao.queryShopCount( shopCondition );
        logger.debug( "店铺总数:" + count );

    }

    @Test
    @Ignore
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owener = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owener.setUserId( 1L );
        area.setAreaId( 2 );
        shopCategory.setShopCategoryId(1L);
        shop.setOwner( owener );
        shop.setArea( area );
        shop.setShopCategory( shopCategory );
        shop.setShopName("测试的店铺117");
        shop.setShopDesc("test1");
        shop.setShopAddr("test2");
        shop.setPhone("12345678902");
        shop.setShopImg( "test1");
        shop.setCreatTime( new Date());
        shop.setEnableStatus( 1 );
        shop.setAdvice( "审核中");

        int effectedNum = shopDao.insertShop( shop );
        assertEquals( 1, effectedNum );
    }

    @Test
    @Ignore
    public void testupdateShop() {
        Shop shop = new Shop();
        shop.setShopId( 2L );
        shop.setShopName("测试的店铺2");
        shop.setShopDesc("测试描述2");
        shop.setLastEditTime( new Date());
        int effectedNum = shopDao.updateShop( shop );
        assertEquals( 1, effectedNum );
    }

    @Test
    public void testQueryByShopId(){
        long shopId = 2;
        Shop shop = shopDao.queryByShopId( shopId );
        logger.debug( "area=[id{},name{}]|", shop.getArea().getAreaId(),shop.getArea().getAreaName() );
    }
}
