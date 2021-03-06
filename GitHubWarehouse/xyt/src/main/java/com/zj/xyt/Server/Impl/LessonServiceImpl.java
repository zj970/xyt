package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Mapper.LessonMapper;
import com.zj.xyt.Server.LessonService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Api(tags = "课程的业务实现层")
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonMapper lessonMapper;

    @Override
    public List<LessonVo> queryAll() {
        return lessonMapper.queryAll();
    }

    @Override
    public List<LessonVo> getList(LessonVo lessonVo, PageUtil pageUtil) throws Exception {
        return lessonMapper.getList(lessonVo, pageUtil);
    }

    @Override
    public int getCount() {
        return lessonMapper.getCount();
    }

    @Override
    public int getCountByLnu(String Lnu) {
        return lessonMapper.getCountByLnu(Lnu);
    }

    @Override
    public LessonVo queryByLnu(String Lnu) {
        return lessonMapper.queryByLnu(Lnu);
    }

    @Override
    public List<LessonVo> queryChoiceListBySnu(int isAll, String searchKey, String Snu, PageUtil pageUtil) {
        return lessonMapper.queryChoiceListBySnu(isAll, searchKey, Snu, pageUtil);
    }

    @Override
    public List<LessonVo> queryChoiceListByTnu(String Tnu, PageUtil pageUtil) {
        return lessonMapper.queryChoiceListByTnu(Tnu, pageUtil);
    }

}
