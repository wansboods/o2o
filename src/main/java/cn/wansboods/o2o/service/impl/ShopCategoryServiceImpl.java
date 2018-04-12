package cn.wansboods.o2o.service.impl;

import cn.wansboods.o2o.base.BaseService;
import cn.wansboods.o2o.dao.ShopCategoryDao;
import cn.wansboods.o2o.entity.ShopCategory;
import cn.wansboods.o2o.service.ShopCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopCategoryServiceImpl extends BaseService<ShopCategoryDao> implements ShopCategoryService {
    public List<ShopCategory> getShopCategoryList( ShopCategory shopCategoryCondition ){
        return baseEntityMapper.queryShopCategory( shopCategoryCondition );
    }
}
