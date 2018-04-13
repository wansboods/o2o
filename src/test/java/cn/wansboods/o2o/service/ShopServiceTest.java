package cn.wansboods.o2o.service;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.entity.Area;
import cn.wansboods.o2o.entity.PersonInfo;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.entity.ShopCategory;
import cn.wansboods.o2o.enums.ShopStateEmum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest{

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
        shop.setShopName("测试的店铺118");
        shop.setShopDesc("test2");
        shop.setShopAddr("test2");
        shop.setPhone("12345678902");
        shop.setCreatTime( new Date());
        shop.setEnableStatus( ShopStateEmum.CHECK.getState() );
        shop.setAdvice( "审核中");

        File shopImg = new File( "E:\\img\\testImg2.jpg");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream( shopImg );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ShopExecution se = shopService.addShop( shop, inputStream, shop.getShopName() );
        assertEquals( ShopStateEmum.CHECK.getState(), se.getState() );
    }



}
