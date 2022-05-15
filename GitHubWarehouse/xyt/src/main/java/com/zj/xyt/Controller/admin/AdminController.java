package com.zj.xyt.Controller.admin;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.StudentVo;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Entity.TeacherVo;
import com.zj.xyt.Server.AdminService;
import com.zj.xyt.Server.EchartService;
import com.zj.xyt.Server.StudentService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @since 2022/3/12 15:00
 */
@Controller
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdminController {
    @Autowired
    AdminService adminService;
    @Autowired
    EchartService echartService;

    @GetMapping("/userManager")
    public String userManager(){
        return "/system/user/form";
    }

    @GetMapping("/role")
    public String roleManager(){
        return "/system/role/index";
    }
    //TODO:用户管理

    //TODO:获取所有学生用户
    @RequiresPermissions("user:query")
    @GetMapping("/studentList")
    @ResponseBody
    public Map<String,Object> getUserAllStudent(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer limit) throws Exception{
        Map<String,Object> map = new HashMap<>();
        PageUtil pageUtil = new PageUtil(page,limit);
        //获取行数
        int count = echartService.getStudentCount();
        List<StudentVo> list = adminService.queryStudentVoList(pageUtil);
        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        return map;
    }
    //TODO:获取所有教师用户
    @RequiresPermissions("user:query")
    @GetMapping("/teacherList")
    @ResponseBody
    public Map<String,Object> getUserAllTeacher(@RequestParam(defaultValue = "1") Integer page,
                                                @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        Map<String,Object> map = new HashMap<>();
        PageUtil pageUtil = new PageUtil(page,limit);
        List<TeacherVo> list = adminService.queryTeacherVoList(pageUtil);
        int count = echartService.getTeacherCount();
        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        return map;
    }


    @RequestMapping("/updateStudent")
    @ResponseBody
    public Map<String,Object> updateUser(Student student) throws Exception{
        Map<String, Object> map = new HashMap<>();
        return map;
    }

}
