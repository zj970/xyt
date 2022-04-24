package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Mapper.StudentMapper;
import com.zj.xyt.Server.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    /**
     * 查询学生表的所有信息
     * @return
     */
    @Override
    public List<Student> queryStudentList() {
        return studentMapper.queryStudentList();
    }

    @Override
    public Student queryStudentByID(String Snu) {
        return studentMapper.queryStudentByID(Snu);
    }

    @Override
    public void insertStudent(Student student) {
        studentMapper.insertStudent(student);
    }
}
