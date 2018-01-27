package cn.wansboods.o2o.service;

import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public interface ShopService {
    ShopExecution addShop(Shop shop, CommonsMultipartFile shopImg ) throws RuntimeException;;

}
