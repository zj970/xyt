package com.zj.xyt.Server;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Score;
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
    /**获取某门课程已选数量*/
    int getCountByLnu(String Lnu);
    /**选课*/
    int choiceCourse(Score score);

    /**根据Snu查询当前学生的可选择课程*/
    List<LessonVo> queryChoiceListBySnu(@Param("Snu") String Snu);
}
