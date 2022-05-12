package com.zj.xyt.Controller.course;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Score;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Server.ScoreService;
import com.zj.xyt.Server.StudentService;
import com.zj.xyt.Server.TeacherService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zj
 * @since 2022/5/12 15:00
 */
@Controller
@Api(tags = "成绩选课")
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    ScoreService scoreService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;

    //教师查看选我课的学生成绩列表 进行打分
    @RequestMapping("/scoreIndex")
    public String scoreIndex() throws Exception {
        return "/system/score/studentScoreIndex";
    }

    //学生查看我的成绩页面
    @RequestMapping("/myScoreIndex")
    public String myScoreIndex() throws Exception {
        return "/system/score/myScoreIndex";
    }

    //教师查看我的学生选课信息
    @RequestMapping("/myStudentIndex")
    public String myStudentIndex() throws Exception {
        return "/system/score/myStudentIndex";
    }

    /**
     * 学生选课
     * @param lnu
     * @return
     * @throws Exception
     */
    @GetMapping("/choiceCourse")
    @ResponseBody
    public Map<String, Object> choiceCourse(@RequestParam(defaultValue="")String lnu) throws Exception {
        Map<String, Object> map = new HashMap<>();
        //System.out.println(lnu+"============");
        if (lnu != null) {
            //获取学生信息
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();//获取User对象
            String username = student.getSname();
            //System.out.println("学生用户名为:"+username);
            //TODO:判断学生是否已选择了这门课
            if (username == null || username.equals("")){
                map.put("result",false);
                map.put("msg","出错了！");
            }else {
                Score score = new Score();
                score.setSnu(student.getSnu());
                score.setLnu(lnu);
                int res = scoreService.choiceCourse(score);
                if (res > 0) {
                    map.put("result",true);
                    map.put("msg","选课成功！");
                }else {
                    map.put("result",false);
                    map.put("msg","人数已满，选课失败！");
                }
            }
        }else {
            map.put("result",false);
            map.put("msg","选课失败，请联系管理员！");
        }

        return map;
    }
    /**
     * 学生退课
     * @param lnu
     * @return
     * @throws Exception
     */
    @RequestMapping("/outCourse")
    @ResponseBody
    public Map<String, Object> outCourse(@RequestParam(defaultValue="")String lnu) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (lnu != null) {
            //获取学生信息
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();//获取User对象
            if (student.getSname() == null || student.getSname().equals("")){
                map.put("result",false);
                map.put("msg","出错了！");
            }else{
                //TODO:退课应该有时间选项，这里直接退课----后续开发完善数据库--增加开始时间---结束时间
                //简单判断是否有成绩，如果有成绩就不能取消成绩
                Float grade = scoreService.queryGradeBySnu(student.getSnu(),lnu);
                if(grade!=null){
                    map.put("result",false);
                    map.put("msg","已经开课，无法退课！");
                }else{
                    int res = scoreService.deleteScore(student.getSnu(),lnu);
                    if (res > 0) {
                        map.put("result",true);
                        map.put("msg","退选成功！");
                    }else {
                        map.put("result",false);
                        map.put("msg","退课失败！");
                    }
                }
            }
        }else {
            map.put("result",false);
            map.put("msg","退课失败，请联系管理员！");
        }

        return map;
    }


    @RequestMapping("/myScore")
    @ResponseBody
    public Map<String, Object> myScore(@RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "10") Integer limit) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (SecurityUtils.getSubject().getPrincipal() instanceof Student){
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            PageUtil pageUtil = new PageUtil(page,limit);
            List<Score> list = scoreService.queryBySnuList(student.getSnu(),pageUtil);
            map.put("data",list);
            map.put("code",0);
            map.put("msg","");
        }
        else {

            map.put("code",1);
            map.put("msg","没有查询相关成绩");
        }
        return map;
    }

}
