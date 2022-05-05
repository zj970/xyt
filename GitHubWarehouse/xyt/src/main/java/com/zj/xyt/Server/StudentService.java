package com.zj.xyt.Server;

import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.StudentVo;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *     学生业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "学生个人信息管理")
public interface StudentService {
    /**
     * 学生视图基本信息
     * @param Snu 账号
     * @return 学生信息视图
     */
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
