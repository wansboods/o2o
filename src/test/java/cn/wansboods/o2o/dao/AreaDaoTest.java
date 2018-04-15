package cn.wansboods.o2o.dao;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.entity.Area;
import cn.wansboods.o2o.entity.Shop;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

import static org.junit.Assert.assertEquals;

public class AreaDaoTest extends BaseTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    @Ignore
    public void testQueryArea(){
        List<Area> areaList = areaDao.queryArea();
        assertEquals( 2,areaList.size() );
    }



}
