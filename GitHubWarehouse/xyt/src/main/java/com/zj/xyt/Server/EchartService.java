package com.zj.xyt.Server;

import com.zj.xyt.Entity.LessonVo;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

/**
 *<p>
 *     班级业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "表格")
public interface EchartService {
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
