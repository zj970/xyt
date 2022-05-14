package com.zj.xyt.Server.Impl;

import com.zj.xyt.Entity.Score;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Mapper.LessonMapper;
import com.zj.xyt.Mapper.ScoreMapper;
import com.zj.xyt.Server.ScoreService;
import com.zj.xyt.utils.PageUtil;
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
        System.out.println("======-----"+score.getLnu());
        Integer choiceNum = scoreMapper.getCountByLnu(score.getLnu());
        if (choiceNum<=lessonMapper.queryByLnu(score.getLnu()).getLnum()){
            //人数不满可以选课
            return scoreMapper.insertSelective(score);
        } else {
            return 0;
        }
    }

    @Override
    public Float queryGradeBySnu(String Snu, String Lnu) {
        return scoreMapper.queryGradeBySnu(Snu, Lnu);
    }

    @Override
    public int deleteScore(String Snu, String Lnu) {
        return scoreMapper.deleteScore(Snu,Lnu);
    }

    @Override
    public List<Score> queryBySnuList(String Snu, PageUtil pageUtil) {
        return scoreMapper.queryBySnuList(Snu, pageUtil);
    }

    @Override
    public int getCountByTnuAndCnu(String Tnu, String Lnu, String Cnu) {
        return scoreMapper.getCountByTnuAndCnu(Tnu, Lnu, Cnu);
    }

    @Override
    public List<StudentVo> getStudentSelectLessonListByTnu(String Tnu, String Lnu, String Cnu, PageUtil pageUtil) {
        return scoreMapper.getStudentSelectLessonListByTnu(Tnu, Lnu, Cnu, pageUtil);
    }

    @Override
    public int updateScore(Score score) throws Exception {
        return scoreMapper.updateScore(score);
    }

    @Override
    public int updateScoreByScoreList(List<Score> scoreList) throws Exception {
        return scoreMapper.updateScoreByScoreList(scoreList);
    }


}
