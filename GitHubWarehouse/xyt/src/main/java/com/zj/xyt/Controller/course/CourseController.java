package com.zj.xyt.Controller.course;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Entity.Teacher;
import com.zj.xyt.Server.LessonService;
import com.zj.xyt.Server.StudentService;
import com.zj.xyt.Server.TeacherService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
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
    /**
     * 返回可选课程列表（可选：人数未满、课程开始时间在当前时间之后）
     * @param page
     * @param limit
     * @param isAll
     * @param searchKey
     * @return
     */
    @GetMapping(value="/choiceList")
    @ResponseBody
    public Map<String, Object> getCourseChoiceList(@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer limit,
                                                   @RequestParam(defaultValue="1") int isAll,
                                                   @RequestParam(defaultValue="")String searchKey) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(SecurityUtils.getSubject().getPrincipal() instanceof Student){
            //查询学生的可选课信息
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            PageUtil pageUtil = new PageUtil(page,limit);
            //1.从课表中选择适合的课程
            System.out.println(searchKey+"-------");//搜索框取消---searchKey获取不到值
            List<LessonVo> list = lessonService.queryChoiceListBySnu(isAll,searchKey,student.getSnu(),pageUtil);
            //2.条件 1 没有选择的 2 人数不够的
            for (LessonVo e: list
            ) {
                e.setChoiceNum(lessonService.getCountByLnu(e.getLnu()));
                System.out.println(e.getTname()+"------------------");
            }
            //map.put("count",count);
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
            //3.返回可以选择的课程信息
        }
        return map;
    }

    /**
     * 返回教师自己教的课程列表
     */
    @ResponseBody
    @RequestMapping(value="/getMyCourseList")
    public Map<String, Object> getMyCourseList(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer limit) {
        Map<String,Object> map = new HashMap<>();
        //获取用户信息
        if(SecurityUtils.getSubject().getPrincipal() instanceof Teacher){
            Teacher teacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
            PageUtil pageUtil = new  PageUtil(page,limit);
            List<LessonVo> list = lessonService.queryChoiceListByTnu(teacher.getTnu(),pageUtil);
            for (LessonVo e: list
            ) {
                e.setChoiceNum(lessonService.getCountByLnu(e.getLnu()));
                System.out.println(e.getTname()+"------------------");
            }
            if (list.size() < 1){
                map.put("code",1);
                map.put("msg","您还没有教授课程");
            }else {
                map.put("data",list);
                map.put("code",0);
                map.put("msg","");
            }
        }

        return map;
    }
}
