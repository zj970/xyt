package com.zj.xyt.Controller.student;

import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Server.StudentService;
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
 * @since 2022/3/12 15:00
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生接口")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/query/{Snu}")
    @ApiOperation(value="查询学生个人信息", notes="通过账户进行查询")
    public StudentVo queryStudentVoBySnu(@ApiParam(value = "账号", required = true)@PathVariable("Snu") String Snu){
        return studentService.queryStudentVo(Snu);
    }
}
