package com.zj.xyt.Impl;

import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.Mapper.TeacherMapper;
import com.zj.xyt.Server.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public TeacherVo queryTeacher(String Tnu) {
        return teacherMapper.queryTeacher(Tnu);
    }

    @Override
    public Teacher queryTeacherByID(String Tnu) {
        return teacherMapper.queryTeacherByID(Tnu);
    }
}
