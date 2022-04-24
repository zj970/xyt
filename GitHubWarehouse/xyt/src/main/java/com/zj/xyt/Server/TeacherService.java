package com.zj.xyt.Server;

import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Entity.TeacherVo;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

/**
 *<p>
 *     教师业务类
 *</p>
 * @author zj970
 */
@Service
@Api(tags = "教师个人信息管理")
public interface TeacherService {
    /**
     * 查询老师的个人信息
     * @param Tnu 账号
     * @return 教师信息
     */
    TeacherVo queryTeacher(String Tnu);
    /**
     * 查询老师的个人信息
     * @param Tnu 账号
     * @return 教师信息
     */
    Teacher queryTeacherByID(String Tnu);
}
