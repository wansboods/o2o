package cn.wansboods.o2o.controller;

import cn.wansboods.o2o.base.BaseController;
import cn.wansboods.o2o.model.Area;
import cn.wansboods.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping( "/superadmin" )
public class AreaController extends BaseController<AreaService>{
    Logger logger = LoggerFactory.getLogger( AreaController.class );

    @RequestMapping( value = "/listarea.do", method = RequestMethod.GET )
    @ResponseBody
    private Map<String,Object> listArea(){
        logger.info( "=== 开始运行{} ===", "测试数据" );
        long startTime = System.currentTimeMillis();
        Map<String,Object> modelMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        try{
            list = baseService.getAreaList();
            modelMap.put( "success", true );
            modelMap.put( "rows", list );
            modelMap.put( "total", list.size() );
        }catch (Exception e){
            e.printStackTrace();
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.toString() );
        }

        logger.error( "test error!" );
        long endTime = System.currentTimeMillis();
        logger.debug( "costTime:[{}ms]", endTime - startTime );
        System.out.print( ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" );
        logger.info( "=== 结束运行{} ===", "测试数据" );
        return modelMap;
    }
}
