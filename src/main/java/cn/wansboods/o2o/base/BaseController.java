package cn.wansboods.o2o.base;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseController<T> {
    @Autowired
    protected  T baseService;
}
