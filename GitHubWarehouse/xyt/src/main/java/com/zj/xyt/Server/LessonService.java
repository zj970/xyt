package com.zj.xyt.Server;

import com.zj.xyt.Entity.LessonVo;
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
public interface LessonService {
    /**
     * 查询所有课程
     */
    List<LessonVo> queryAll();

    /**
     * 查询课程
     */
    List<LessonVo> getList(LessonVo lessonVo, PageUtil pageUtil) throws Exception;

    int getCount();

    /**根据Lnu查询单个课程*/
    LessonVo queryByLnu(@Param("Lnu") String Lnu);
}
