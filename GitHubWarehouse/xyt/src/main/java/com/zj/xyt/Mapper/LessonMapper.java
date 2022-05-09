package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Lesson;
import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.utils.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zj970
 */
@Repository
@Mapper
public interface LessonMapper {

    /**
     * 查询所有课程
     */
    List<LessonVo> queryAll();

    /**
     * 查询课程
     */
    List<LessonVo> getList(@Param("lessonVo")LessonVo lessonVo, @Param("pageUtil")PageUtil pageUtil) throws Exception;

    int getCount();

    /**根据Lnu查询单个课程*/
    LessonVo queryByLnu(@Param("Lnu") String Lnu);
}
