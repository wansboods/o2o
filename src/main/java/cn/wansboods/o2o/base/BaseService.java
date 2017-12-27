package cn.wansboods.o2o.base;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {
    @Autowired
    protected T baseEntityMapper;
}
