package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StudentMapper {

    /**根据学生学号检索学生信息*/
    Student queryStudent(String Snu);

}
