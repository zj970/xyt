package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Classes;
import com.zj.xyt.Mapper.ClassMapper;
import com.zj.xyt.Server.ClassService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Api(tags = "班级的业务实现层")
public class ClassServiceImpl implements ClassService {

    @Autowired
    ClassMapper classMapper;

    @Override
    public List<Classes> getAll() {
        return classMapper.getAll();
    }

    @Override
    public Classes queryByCnu(String Cnu) {
        return classMapper.queryByCnu(Cnu);
    }
}
