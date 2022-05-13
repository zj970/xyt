package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.Mapper.TeacherMapper;
import com.zj.xyt.Server.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    /**
     * 查询教师的基本信息
     * @param Tnu 账号
     * @return
     */
    @Override
    public TeacherVo queryTeacherVoByTnu(String Tnu) {
        return teacherMapper.queryTeacherVoByTnu(Tnu);
    }

    @Override
    public Teacher queryTeacherByID(String Tnu) {
        return teacherMapper.queryTeacherByID(Tnu);
    }

    @Override
    public List<TeacherVo> findTeacherVoList() throws Exception {
        return teacherMapper.findTeacherVoList();
    }

    @Override
    public List<TeacherVo> findTeacherVoListByDnu(String Dnu) {
        return teacherMapper.findTeacherVoListByDnu(Dnu);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
    }


}
