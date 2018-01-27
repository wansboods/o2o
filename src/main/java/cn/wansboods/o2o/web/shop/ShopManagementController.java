package cn.wansboods.o2o.web.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping( "/shopadmin" )
public class ShopManagementController {

    @RequestMapping( value = "/registershop",method = RequestMethod.POST )
    private Map<String,Object> registerShop(HttpServletRequest request ){
        // 1 接收并转化相应的参数, 包括店铺信息以及图片信息
        // 2 注册店铺
        // 3 返回结果

        return null;
    }
}
