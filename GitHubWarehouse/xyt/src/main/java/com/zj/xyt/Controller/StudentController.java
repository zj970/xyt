package com.zj.xyt.Controller;

import com.zj.xyt.Entity.Admin;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Mapper.AdminMapper;
import com.zj.xyt.Mapper.StudentMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zj
 * @creatDate 2022/3/12 15:00
 */
@RestController
//说明接口文件
@Api(value = "测试接口", tags = "学生相关的接口", description = "用户测试接口")
public class StudentController {
    @Autowired
    private StudentMapper studentMapper;

    /**
     * 根据Snu查询用户
     */
    @GetMapping(value = "findBySnu")
    @ApiOperation(value = "根据Snu获取用户信息",notes = "根据账号查询用户信息")
    public Student getAdmin(String Anu){
        return studentMapper.queryStudent(Anu);
    }
}
