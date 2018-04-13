package cn.wansboods.o2o.web.shopadmin;

import cn.wansboods.o2o.base.BaseController;
import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.entity.Area;
import cn.wansboods.o2o.entity.PersonInfo;
import cn.wansboods.o2o.entity.Shop;
import cn.wansboods.o2o.entity.ShopCategory;
import cn.wansboods.o2o.enums.ShopStateEmum;
import cn.wansboods.o2o.service.AreaService;
import cn.wansboods.o2o.service.ShopCategoryService;
import cn.wansboods.o2o.service.ShopService;
import cn.wansboods.o2o.util.CodeUtil;
import cn.wansboods.o2o.util.HttpServletRequestUtil;
import cn.wansboods.o2o.util.ImageUtil;
import cn.wansboods.o2o.util.PathUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController extends BaseController<ShopService> {

    @Autowired
    private ShopCategoryService shopCategoryService;

    @Autowired
    private AreaService areaService;


//    private static void inputStreamToFile(InputStream ins, File file ){
//        FileOutputStream os = null;
//        try{
//            os = new FileOutputStream( file );
//            int bytesRead = 0;
//            byte[] buffer = new byte[ 10240 ];
//            while ( -1 != ( bytesRead = ins.read( buffer ) ) ){
//                os.write( buffer, 0, bytesRead );
//            }
//        }catch ( Exception e ){
//            throw new RuntimeException( "调用inputStreamToFile产生异常:" + e.getMessage() );
//        }finally {
//            try{
//                if( os != null ){
//                    os.close();
//                }
//
//                if( ins != null ){
//                    ins.close();
//                }
//            }catch (IOException e ){
//                throw new RuntimeException( "inputStreamToFile关闭io产生异常:" + e.getMessage() );
//            }
//        }
//    }

    @RequestMapping(value = "/registershop",method= RequestMethod.POST)
    @ResponseBody
    private Map<String,Object> registerShop(HttpServletRequest request ){

        Map<String,Object> modelMap = new HashMap<String,Object>();
        // 0 验证验证码
        if( !CodeUtil.checkVerifyCode(request)){
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "输入的验证码有误" );
            return modelMap;
        }

        // 1 接收并转化相应的参数, 包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString( request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try{
            shop = mapper.readValue( shopStr, Shop.class );
        }catch ( Exception e ){
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }

        CommonsMultipartFile shopImg = null;
        //上传文件类
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver( request.getSession().getServletContext() );
        if( commonsMultipartResolver.isMultipart( request ) ){
            MultipartHttpServletRequest multipartHttpServletRequest = ( MultipartHttpServletRequest )request;
            shopImg = ( CommonsMultipartFile ) multipartHttpServletRequest.getFile( "shopImg" );
        }else{
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "上传图片不能为空" );
            return modelMap;
        }

        // 2 注册店铺
        if( shop != null && shopImg != null ){
            PersonInfo owmer = new PersonInfo();
            owmer.setUseId( 1L );
            shop.setOwner( owmer );
//            File shopImgFile = new File( PathUtil.getImgBasePath() + ImageUtil.getRandomFileName() );
//            try {
//                shopImgFile.createNewFile();
//            } catch (IOException e) {
//                modelMap.put( "success", false );
//                modelMap.put( "errMsg", e.getMessage() );
//                return modelMap;
//            }

//            try{
//                inputStreamToFile( shopImg.getInputStream( ), shopImgFile );
//            }catch( IOException e ){
//                modelMap.put( "success", false );
//                modelMap.put( "errMsg", e.getMessage() );
//                return modelMap;
//            }

            ShopExecution se = null;
            try {
                se = baseService.addShop( shop, shopImg.getInputStream(), shopImg.getOriginalFilename() );
                if( se.getState() == ShopStateEmum.CHECK.getState() ){
                    modelMap.put( "success", true );
                    modelMap.put( "errMsg", se.getStateInfo() );
                }else{
                    modelMap.put( "success", false );
                    modelMap.put( "errMsg", se.getStateInfo() );
                }
            } catch (IOException e) {
                modelMap.put( "success", false );
                modelMap.put( "errMsg", e.getMessage() );
            }
            return modelMap;
        }else{
            modelMap.put( "success", false );
            modelMap.put( "errMsg", "请输入店铺信息" );
            return modelMap;
        }
    }


    @RequestMapping(value = "/getshopinitinfo",method = RequestMethod.GET )
    @ResponseBody
    private Map<String,Object> getShopInitInfo(){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList( new ShopCategory() );
            areaList = areaService.getAreaList();
            modelMap.put( "shopCategoryList", shopCategoryList );
            modelMap.put( "areaList", areaList );
            modelMap.put( "success", true );
            return modelMap;
        }catch (Exception e ){
            modelMap.put( "success", false );
            modelMap.put( "errMsg", e.getMessage() );
            return modelMap;
        }
    }




}
