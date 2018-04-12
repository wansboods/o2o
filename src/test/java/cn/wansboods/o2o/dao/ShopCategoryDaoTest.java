package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopCategoryDaoTest extends BaseTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void testQueryShopCategory(){
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory( new ShopCategory() );
        assertEquals( 1, shopCategories.size() );
    }
}
