package com.zj.xyt.Mapper;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Score;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.utils.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 持久层-选课记录成绩
 */
@Mapper
public interface ScoreMapper {

    /**获取某门课程已选数量*/
    int getCountByLnu(@Param("Lnu") String Lnu);

    /**添加选课记录*/
    int insertSelective(Score score);

    /**根据Snu查询当前学生是否有成绩*/
    Float queryGradeBySnu(@Param("Snu")String Snu,@Param("Lnu")String Lnu);

    /**删除选课记录*/
    int deleteScore(@Param("Snu")String Snu,@Param("Lnu")String Lnu);

    /**查询学生成绩*/
    List<Score> queryBySnuList(@Param("Snu")String Snu, @Param("pageUtil") PageUtil pageUtil);

    /**查询教师的选课学生数量*/
    int getCountByTnuAndCnu(@Param("Tnu") String Tnu,@Param("Lnu") String Lnu,@Param("Cnu") String Cnu);

    /**根据教师的Tnu查询学生选课信息*/
    List<StudentVo> getStudentSelectLessonListByTnu(@Param("Tnu") String Tnu,@Param("Lnu") String Lnu,@Param("Cnu") String Cnu,@Param("pageUtil") PageUtil pageUtil);

    int updateScore(Score score) throws Exception;
    int updateScoreByScoreList(@Param("scoreList")List<Score> scoreList) throws Exception;
}
