package com.zj.xyt.Controller.course;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Entity.Score;
import com.zj.xyt.Entity.Student;
import com.zj.xyt.Server.ScoreService;
import com.zj.xyt.Server.StudentService;
import com.zj.xyt.Server.TeacherService;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/choiceCourse")
    @ResponseBody
    public Map<String, Object> choiceCourse(@RequestParam(defaultValue="")String lnu) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (lnu != null) {
            //获取学生信息
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();//获取EasUser对象
            String username = student.getSname();
            System.out.println("学生用户名为:"+username);
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
     * 返回可选课程列表（可选：人数未满、课程开始时间在当前时间之后）
     * @param page
     * @param limit
     * @param isAll
     * @param searchKey
     * @return
     */
    @RequestMapping(value="/choiceList")
    @ResponseBody
    public Map<String, Object> getCourseChoiceList(@RequestParam(defaultValue = "1") Integer page,
                                                   @RequestParam(defaultValue = "10") Integer limit,
                                                   @RequestParam(defaultValue="1") int isAll,
                                                   @RequestParam(defaultValue="")String searchKey) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if(SecurityUtils.getSubject().getPrincipal() instanceof Student){
            //查询学生的可选课信息
            Student student = (Student) SecurityUtils.getSubject().getPrincipal();
            //1.从课表中选择适合的课程
            List<LessonVo> list = scoreService.queryChoiceListBySnu(student.getSnu());
            //2.条件 1 没有选择的 2 人数不够的
            for (LessonVo e: list
            ) {
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
}
