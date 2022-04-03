package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.TeacherVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 持久层-教师
 */
@Mapper
public interface TeacherMapper {
    //TODO:老师个人信息管理

    /**
     * 查询老师的个人信息
     * @param Tnu 账号
     * @return 教师信息
     */
    TeacherVo queryTeacher(String Tnu);
}
