package cn.wansboods.o2o.service;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.enums.ShopStateEmum;
import cn.wansboods.o2o.model.Area;
import cn.wansboods.o2o.model.PersonInfo;
import cn.wansboods.o2o.model.Shop;
import cn.wansboods.o2o.model.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@Service
public class ShopServiceTest extends BaseTest {
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop(){
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
        shop.setShopName("测试的店铺2");
        shop.setShopDesc("test2");
        shop.setShopAddr("test2");
        shop.setPhone("12345678901");

        shop.setCreatTime( new Date());
        shop.setEnableStatus( ShopStateEmum.CHECK.getState() );
        shop.setAdvice( "审核中");
//        CommonsMultipartFile shopImg = new CommonsMultipartFile( "" );
//        ShopExecution se = shopService.addShop( shop, shopImg );
//        assertEquals( ShopStateEmum.CHECK.getState(), se.getState() );
    }
}
