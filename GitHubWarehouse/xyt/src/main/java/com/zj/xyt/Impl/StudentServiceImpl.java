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

    /**
     * 查询学生的基本信息
     * @param Snu 账号
     * @return
     */
    @Override
    public StudentVo queryStudentVo(String Snu) {
        return studentMapper.queryStudentVo(Snu);
    }
}