package cn.wansboods.o2o.service;

import cn.wansboods.o2o.base.BaseService;
import cn.wansboods.o2o.dao.AreaDao;
import cn.wansboods.o2o.entity.Area;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaService extends BaseService<AreaDao>{
    public List<Area> getAreaList(){
        return baseEntityMapper.queryArea();
    }
}
