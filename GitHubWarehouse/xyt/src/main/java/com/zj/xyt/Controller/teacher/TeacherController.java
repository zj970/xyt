package com.zj.xyt.Controller.teacher;

import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.Impl.TeacherServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @since 2022/4/2 15:00
 */
@RestController
@RequestMapping("/teacher")
@Api(tags = "教师接口")
public class TeacherController {
    @Autowired
    TeacherServiceImpl teacherService;

    @GetMapping("/query/{Tnu}")
    @ApiOperation(value="查询教师个人信息", notes="通过账户进行查询")
    public TeacherVo queryTeacherVoByTnu(@ApiParam(value = "账号", required = true)@PathVariable("Tnu") String Tnu){
        return teacherService.queryTeacher(Tnu);
    }
}
