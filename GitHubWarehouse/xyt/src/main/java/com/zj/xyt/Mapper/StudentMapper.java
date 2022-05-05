package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.StudentVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 学生的持久层
 * @author zj970
 */
@Mapper
public interface StudentMapper {
    //TODO:学生的基本信息展示
    StudentVo queryStudentVo(String Snu);

    /**
     * 查询学生的所有信息
     * @return 学生表中的所有信息
     */
    List<Student> queryStudentList();

    /**
     * 查询学生信息
     * @return 学生表
     */
    Student queryStudentByID(String Snu);

    /**
     * 注册学生账号
     */
    void insertStudent(Student student);

    List<Student> findList(Student student);
}
