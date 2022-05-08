package com.zj.xyt.Controller.student;

import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Server.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Controller
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

    @GetMapping("/index")
    public String index() throws Exception{
        return "/system/student/index";
    }


    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Page<StudentVo> pager = PageHelper.startPage(page,limit);
        if ( SecurityUtils.getSubject().getPrincipal() instanceof Student){
            Student student = (Student)SecurityUtils.getSubject().getPrincipal();
            String snu = student.getSnu();
            StudentVo studentVo =  studentService.queryStudentVo(snu);
            List<StudentVo> list = new ArrayList<>();
            list.add(studentVo);
            System.out.println("登录是学生账号");
            map.put("count",pager.getTotal());
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
        }
        else if(SecurityUtils.getSubject().getPrincipal() instanceof Teacher) {
            Teacher teacher = (Teacher)SecurityUtils.getSubject().getPrincipal();
            String tnu = teacher.getTnu();
            List<StudentVo> list = studentService.headteacherFindList(tnu);
            System.out.println("获取信息总条数为:" + list.size());
            for (StudentVo e:list
            ) {
                System.out.println(e.getSnu());
                System.out.println(e.getSname());
            }
            map.put("count",pager.getTotal());
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
        } else {
            List<StudentVo> list = studentService.findList();
            System.out.println("获取信息总条数为:" + list.size());
            for (StudentVo e:list
            ) {
                System.out.println(e.getSnu());
                System.out.println(e.getSname());
            }
            map.put("count",pager.getTotal());
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
        }


        return map;
    }
}
