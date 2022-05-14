package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.LessonVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zj970
 */
@Repository
@Mapper
public interface EchartMapper {


    /**
     * 返回通过的人数
     */
    int getToalPass(@Param("Lnu") String Lnu);
    /**
     * 返回不通过的人数
     */
    int getToalNOPass(@Param("Lnu") String Lnu);

    /**
     * 根据Lnu返回LessonVo
     */
    LessonVo getLessonVoByLnu(@Param("Lnu") String Lnu);

    /**
     * 获取教师的人数
     */
    int getTeacherCount();
    /**
     * 获取学生人数
     */
    int getStudentCount();
    /**
     * 获取学生中的女生男生人数
     */
    int getStuentBySex(@Param("Ssex") String Ssex);

}
