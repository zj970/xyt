package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Score;
import com.zj.xyt.Mapper.LessonMapper;
import com.zj.xyt.Mapper.ScoreMapper;
import com.zj.xyt.Server.ScoreService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Api(tags = "课程的业务实现层")
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;
    @Autowired
    LessonMapper lessonMapper;

    @Override
    public int getCountByLnu(String Lnu) {
        return scoreMapper.getCountByLnu(Lnu);
    }

    /**
     * 选课是一个事务
     * @param score
     * @return
     */
    @Override
    public int choiceCourse(Score score) {
        //选课过程为一个事务
        Integer choiceNum = scoreMapper.getCountByLnu(score.getLnu());
        if (choiceNum<=lessonMapper.queryByLnu(score.getLnu()).getLnum()){
            //人数不满可以选课
            return scoreMapper.insertSelective(score);
        } else {
            return 0;
        }
    }

    @Override
    public List<LessonVo> queryChoiceListBySnu(String Snu) {
        return scoreMapper.queryChoiceListBySnu(Snu);
    }
}
