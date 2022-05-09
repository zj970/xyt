package com.zj.xyt.Controller.course;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Server.LessonService;
import com.zj.xyt.Server.StudentService;
import com.zj.xyt.Server.TeacherService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @since 2022/5/12 15:00
 */
@Controller
@Api(tags = "课程接口")
@RequestMapping("/course")
public class CourseController {
    @Autowired
    LessonService lessonService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;


    @RequestMapping("/adminIndex")
    @RequiresPermissions("course:adminIndex")
    public String adminIndex() throws Exception {
        return "/system/course/adminCourseIndex";
    }
    @RequestMapping("/studentIndex")
    @RequiresPermissions("course:studentIndex")
    public String studentIndex() throws Exception {
        return "/system/course/studentCourseIndex";
    }
    @RequestMapping("/teacherIndex")
    @RequiresPermissions("course:teacherIndex")
    public String teacherIndex() throws Exception {
        return "/system/course/teacherCourseIndex";
    }

    @RequestMapping("/courseAddForm")
    public String courseAddForm() throws Exception{
        return "/system/course/courseAddForm";
    }

    @RequestMapping("/courseEditForm")
    public String courseEditForm() throws Exception{
        return "system/course/courseEditForm";
    }
    @RequestMapping("/baseCourseAddForm")
    public String baseCourseAddForm() throws Exception {
        return "/system/baseCourse/baseCourseAddForm";
    }

    @RequestMapping("/getCourseList")
    @ResponseBody
    public Map<String,Object> getCourseList(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer limit,
                                            LessonVo lessonVo) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int count = lessonService.getCount();
        PageUtil pageUtil = new  PageUtil(page,limit);
        List<LessonVo> list = lessonService.getList(lessonVo,pageUtil);
        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");

        return map;
    }

}
