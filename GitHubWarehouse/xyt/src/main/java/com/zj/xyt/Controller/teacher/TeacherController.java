package com.zj.xyt.Controller.teacher;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zj.xyt.Entity.*;
import com.zj.xyt.Server.ClassService;
import com.zj.xyt.Server.TeacherService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
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
 * @since 2022/4/2 15:00
 */
@Controller
@RequestMapping("/teacher")
@Api(tags = "教师接口")
public class TeacherController {
    //TODO:教师逻辑
    @Autowired
    TeacherService teacherService;
    @Autowired
    ClassService classService;

    @GetMapping("/index")
    public String index() throws Exception{
        return "/system/teacher/index";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) throws Exception{
        Map<String, Object> map = new HashMap<>();
        Page<TeacherVo> pager = PageHelper.startPage(page,limit);
        if ( SecurityUtils.getSubject().getPrincipal() instanceof Student) {
            Student student = (Student)SecurityUtils.getSubject().getPrincipal();
            Classes classes = classService.queryByCnu(student.getCnu());
            List<TeacherVo> list = teacherService.findTeacherVoListByDnu(classes.getDnu());
            map.put("count",pager.getTotal());
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
        } else{
            List<TeacherVo> list = teacherService.findTeacherVoList();
            map.put("count",pager.getTotal());
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
        }
        return map;
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<TeacherVo> search() throws Exception{
        return  teacherService.findTeacherVoList();
    }
}
