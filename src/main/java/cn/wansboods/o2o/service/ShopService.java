package cn.wansboods.o2o.service;

import cn.wansboods.o2o.dto.ShopExecution;
import cn.wansboods.o2o.entity.Shop;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;


public interface ShopService {
    ShopExecution addShop(Shop shop, File shopImg );

}
