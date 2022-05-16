package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Classes;
import com.zj.xyt.Mapper.ClassMapper;
import com.zj.xyt.Server.ClassService;
import com.zj.xyt.utils.PageUtil;
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

    @Override
    public List<Classes> getList(Classes classes, PageUtil pageUtil) {
        return classMapper.getList(classes, pageUtil);
    }

    @Override
    public void addClass(Classes classes) {
        classMapper.addClass(classes);
    }

    @Override
    public void batchDeleteClass(String[] cnus) {
            classMapper.batchDeleteClass(cnus);
    }

    @Override
    public Classes getClassView(String cnu) {
        return classMapper.getClassView(cnu);
    }

    @Override
    public void updateClass(Classes classes) {
        classMapper.updateClass(classes);
    }

    @Override
    public List<Classes> findClass(String cnu) {
        return classMapper.findClass(cnu);
    }

    @Override
    public int getCount() {
        return classMapper.getCount();
    }
}
