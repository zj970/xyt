package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Mapper.EchartMapper;
import com.zj.xyt.Server.EchartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Api(tags = "表格的业务实现层")
public class EchartServiceImpl implements EchartService {

    @Autowired
    EchartMapper echartMapper;


    @Override
    public int getToalPass(String Lnu) {
        return echartMapper.getToalPass(Lnu);
    }

    @Override
    public int getToalNOPass(String Lnu) {
        return echartMapper.getToalNOPass(Lnu);
    }

    @Override
    public LessonVo getLessonVoByLnu(String Lnu) {
        return echartMapper.getLessonVoByLnu(Lnu);
    }

    @Override
    public int getTeacherCount() {
        return echartMapper.getTeacherCount();
    }

    @Override
    public int getStudentCount() {
        return echartMapper.getStudentCount();
    }

    @Override
    public int getStuentBySex(String Ssex) {
        return echartMapper.getStuentBySex(Ssex);
    }
}
