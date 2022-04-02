package com.zj.xyt.Impl;

import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Mapper.StudentMapper;
import com.zj.xyt.Server.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;

    @Override
    public StudentVo queryStudentVo(String Snu) {
        return studentMapper.queryStudentVo(Snu);
    }
}
