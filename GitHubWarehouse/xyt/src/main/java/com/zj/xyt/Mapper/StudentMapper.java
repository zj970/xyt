package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.StudentVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
    //TODO:学生的基本信息展示
    StudentVo queryStudentVo(String Snu);
}
