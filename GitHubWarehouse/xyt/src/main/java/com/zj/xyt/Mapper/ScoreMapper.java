package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 持久层-选课记录成绩
 */
@Mapper
public interface ScoreMapper {

    /**获取某门课程已选数量*/
    int getCountByLnu(@Param("Lnu") String Lnu);

    /**添加选课记录*/
    int insertSelective(Score score);
}
