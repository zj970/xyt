package com.zj.xyt.Server;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Score;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *<p>
 *     班级业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "课程业务层")
public interface ScoreService {
    /**
     * 获取某门课程已选数量
     */
    int getCountByLnu(String Lnu);

    /**
     * 选课
     */
    int choiceCourse(Score score);

    /**
     * 根据Snu查询当前学生是否有成绩
     */
    Float queryGradeBySnu(@Param("Snu") String Snu, @Param("Lnu") String Lnu);

    /**
     * 删除选课记录
     */
    int deleteScore(@Param("Snu") String Snu, @Param("Lnu") String Lnu);

    /**
     * 查询学生成绩
     */
    List<Score> queryBySnuList(@Param("Snu") String Snu, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 查询教师的选课学生数量
     */
    int getCountByTnuAndCnu(@Param("Tnu") String Tnu, @Param("Lnu") String Lnu, @Param("Cnu") String Cnu);

    /**
     * 根据教师的Tnu查询学生选课信息
     */
    List<StudentVo> getStudentSelectLessonListByTnu(@Param("Tnu") String Tnu, @Param("Lnu") String Lnu, @Param("Cnu") String Cnu, @Param("pageUtil") PageUtil pageUtil);

    int updateScore(Score score) throws Exception;

    int updateScoreByScoreList(@Param("scoreList") List<Score> scoreList) throws Exception;
}
