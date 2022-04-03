package com.zj.xyt.Server;

import com.zj.xyt.Entity.StudentVo;
import io.swagger.annotations.Api;

/**
 *<p>
 *     学生业务类
 *</p>
 * @author zj970
 */
@Api(tags = "学生个人信息管理")
public interface StudentService {
    /**
     * 学生视图基本信息
     * @param Snu 账号
     * @return 学生信息视图
     */
    StudentVo queryStudentVo(String Snu);
}
