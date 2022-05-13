package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.*;
import com.zj.xyt.Server.ClassService;
import com.zj.xyt.Server.NoticeService;
import com.zj.xyt.Server.StudentService;
import com.zj.xyt.Server.TeacherService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;

import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * 用于控制主页的各种信息
 * @author zj970
 * @Date:2021 4/28
 */
@Api(tags = "主页控制器")
@RequestMapping("/main")
@Controller
public class MainController {
    @Autowired
    NoticeService noticeService;
    @Autowired
    StudentService studentService;
    @Autowired
    ClassService classService;
    @Autowired
    TeacherService teacherService;

    @GetMapping("/home")
    public String main() throws Exception{
        return "/system/home/homePage";
    }
    @RequestMapping("/basicForm")
    public String basicForm(ModelMap modelMap) throws Exception {
        modelMap.put("classList",classService.getAll());
        return "/system/user/form2";
    }
    @RequestMapping(value="/getNotice",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getNotice(@RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "2") Integer limit,
                                        Notice notice) throws Exception {
        Map<String,Object> map = new HashMap<>();
        System.out.println("模糊查询的内容为:"+notice.getContent());
         Subject subject = SecurityUtils.getSubject();//获取当前登录用户信息
        List<Integer> rolelist = new ArrayList<>();
        //判断用户类型，
        if (subject.hasRole("student")){
            rolelist.add(1);
        }else if (subject.hasRole("teacher")){
            rolelist.add(2);

        }else if (subject.hasRole("admin")){
            rolelist.add(0);
        }

        PageUtil pageUtil = new PageUtil(page, limit);
        if (rolelist.size() >= 2) {
            int type = 3;
            int count = noticeService.getCountByTypeAndNotice(type, notice);
            List<Notice> list = noticeService.getNoticeListByTypeAndNotice(type, notice, pageUtil);

            pageUtil.setTotal(count);
            pageUtil.setCount(limit);
            int totalPage = pageUtil.getTotalPage();

            map.put("totalPage", totalPage);

            map.put("count", count);
            map.put("data", list);
            map.put("code", 0);
            map.put("msg", "");
        } else {
            if (rolelist.size() == 0 || rolelist.get(0) == 2) {
                //type = 1 全员可见 type = 2 教师可见  type = 3 草稿  管理员可见
                int type = 1;
                int count = noticeService.getCountByTypeAndNotice(type, notice);
                pageUtil.setTotal(count);
                pageUtil.setCount(limit);
                int totalPage = pageUtil.getTotalPage();
//            System.out.println("总页数为"+totalPage);

                List<Notice> list = noticeService.getNoticeListByTypeAndNotice(type, notice, pageUtil);

                map.put("totalPage", totalPage);
                map.put("count", count);
                map.put("data", list);
                map.put("code", 0);
                map.put("msg", "");
            } else if (rolelist.get(0) == 3) {
                int type = 2;
                int count = noticeService.getCountByTypeAndNotice(type, notice);
                List<Notice> list = noticeService.getNoticeListByTypeAndNotice(type, notice, pageUtil);
                pageUtil.setTotal(count);
                pageUtil.setCount(limit);
                int totalPage = pageUtil.getTotalPage();
//            System.out.println("总页数为"+totalPage);

                map.put("totalPage", totalPage);
                map.put("count", count);
                map.put("data", list);
                map.put("code", 0);
                map.put("msg", "");
            } else {
                int type = 3;
                int count = noticeService.getCountByTypeAndNotice(type, notice);
                List<Notice> list = noticeService.getNoticeListByTypeAndNotice(type, notice, pageUtil);
                pageUtil.setTotal(count);
                pageUtil.setCount(limit);
                int totalPage = pageUtil.getTotalPage();
                map.put("totalPage", totalPage);
                map.put("count", count);
                map.put("data", list);
                map.put("code", 0);
                map.put("msg", "");
            }
        }
        return map;
    }

    //点击查看具体通知
    @RequestMapping(value="/lookNotice")
    public ModelAndView look(Integer id){
        ModelAndView modelAndView = new ModelAndView("/system/notice/homeNotice");
        System.out.println("我是通知id:"+id);
        List<Notice> list = noticeService.getNoticeById(id);
        modelAndView.addObject("noticeList",list);
        //modelAndView.setViewName("system/notice/homeNotice");
        return modelAndView;
    }


    @RequestMapping("/basicInformationIndex")
    public String basicInformationIndex(Model model) throws Exception {
        //1. 获取身份信息 判断是哪个角色
        if (SecurityUtils.getSubject().getPrincipal() instanceof Student){
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            StudentVo studentVo = studentService.queryStudentVo(student.getSnu());
            model.addAttribute("data",studentVo);
            model.addAttribute("code",2);
            model.addAttribute("msg","学生信息成功");
        }else if (SecurityUtils.getSubject().getPrincipal() instanceof Teacher){
            Teacher teacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
            TeacherVo teacherVo = teacherService.queryTeacherVoByTnu(teacher.getTnu());
            model.addAttribute("data",teacherVo);
            model.addAttribute("code",3);
            model.addAttribute("msg","教师信息成功");
        }else if (SecurityUtils.getSubject().getPrincipal() instanceof Admin){
            model.addAttribute("code",4);
            model.addAttribute("msg","管理员还没有个人信息，请静候功能开放！");
        } else{
            model.addAttribute("code",1);
            model.addAttribute("msg","该用户还没有个人信息,请稍后再来查看和修改个人信息！");
        }
        return "/system/user/basicInformation";
    }

    /**
     * 获取修改个人资料时用户已有的信息
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getBasicInformation")
    @ResponseBody
    public Object getBasicInformation() throws Exception {
        if (SecurityUtils.getSubject().getPrincipal() instanceof Student){
            Student student = (Student)SecurityUtils.getSubject().getPrincipal();
            return student;
        } else  if (SecurityUtils.getSubject().getPrincipal() instanceof Teacher){
            Teacher teacher = (Teacher) SecurityUtils.getSubject().getPrincipal();
            return  teacher;
        } else {
            System.out.println("我怎么变成null了！");
            return null;
        }
    }
    @RequestMapping(value = "/modifyInformation",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> modifyInformation(Student student,Teacher teacher,String cnu) throws Exception {
        Map<String, Object> map = new HashMap<>();
        System.out.println(student.toString()+"student");
        System.out.println(teacher.toString()+"teacher");
        System.out.println(cnu);
        if (SecurityUtils.getSubject().getPrincipal() instanceof Student) {
            //Student s = (Student) SecurityUtils.getSubject().getPrincipal();
            //名称不符合必须重新set课程id
            //student.setCnu(cnu);
            studentService.updateStudent(student);
            map.put("result", true);
            return map;
        } else if (SecurityUtils.getSubject().getPrincipal() instanceof Teacher) {
            teacherService.updateTeacher(teacher);
            map.put("result", true);
            return map;
        } else {
            System.out.println("我怎么变成null了！");
            map.put("result", false);
            return null;
        }
    }
}
