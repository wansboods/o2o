package cn.wansboods.o2o.service;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.entity.Area;
import cn.wansboods.o2o.entity.PersonInfo;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.entity.ShopCategory;
import cn.wansboods.o2o.enums.ShopStateEmum;
import cn.wansboods.o2o.exceptions.ShopOperationException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ShopServiceTest extends BaseTest{

    Logger logger =  LoggerFactory.getLogger( ShopServiceTest.class );
    @Autowired
    private ShopService shopService;

    @Test
    public void testAddShop(){
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

    @Test
    public void testModifyShop() throws ShopOperationException,FileNotFoundException{
        Shop shop = new Shop();
        shop.setShopId( 2L );
        shop.setShopName( "修改后的店铺名称");

        File shopImg = new File( "E:\\img\\testImg2.jpg");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream( shopImg );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ShopExecution shopExecution = shopService.modifyShop( shop, inputStream, "testImg2.jpg" );
        logger.info( "getStateInfo={}", shopExecution.getStateInfo());
    }

}
