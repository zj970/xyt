package com.zj.xyt.Server;

import com.zj.xyt.Entity.Score;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

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
}
