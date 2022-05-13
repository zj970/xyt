package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Entity.TeacherVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    TeacherVo queryTeacherVoByTnu(@Param("Tnu")String Tnu);
    /**
     * 查询老师的个人信息
     * @param Tnu 账号
     * @return 教师信息
     */
    Teacher queryTeacherByID(@Param("Tnu")String Tnu);

    /**
     * 查询老师的所有信息
     */
    List<TeacherVo> findTeacherVoList();


    /**
     * 查询本系的老师
     */
    List<TeacherVo> findTeacherVoListByDnu(@Param("Dnu")String Dnu);

    /**
     * 更新教师信息
     */
    void updateTeacher(Teacher teacher);
}
