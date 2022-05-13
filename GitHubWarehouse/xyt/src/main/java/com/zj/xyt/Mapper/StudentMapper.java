package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.StudentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生的持久层
 * @author zj970
 */
@Mapper
public interface StudentMapper {
    //TODO:学生的基本信息展示
    StudentVo queryStudentVo(@Param("Snu")String Snu);

    /**
     * 查询学生的所有信息 - 学生消息
     * @return 学生表中的所有信息
     */
    List<Student> queryStudentList();

    /**
     * 查询学生信息
     * @return 学生表
     */
    Student queryStudentByID(@Param("Snu")String Snu);

    /**
     * 注册学生账号
     */
    void insertStudent(Student student);

    /**
     * 更新学生信息
     */
    void updateStudent(Student student);
    /**
     * 查询学生的所有信息
     */
    List<StudentVo> findList();
    /**
     * 根据班主任Cnu查询学生信息
     */
    List<StudentVo> headteacherFindList(@Param("Tnu")String Tnu);
}
