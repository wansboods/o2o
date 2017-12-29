package cn.wansboods.o2o.service;

import cn.wansboods.o2o.BaseTest;
import cn.wansboods.o2o.model.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class areaSerivceTest extends BaseTest {
    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList(){
        List<Area> areaList = areaService.getAreaList();
        assertEquals( "3", areaList.get(0).getAreaName() );
    }
}
