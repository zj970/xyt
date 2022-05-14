package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Server.EchartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zj
 * @since 2022/5/14 15:00
 */
@Controller
@Api(tags = "表格接口")
@RequestMapping("/echart")
public class EchartController {

    @Autowired
    EchartService echartService;

    @RequestMapping("/scoreEchart")
    public String scoreEchart(){
        return "/echarts/ScoreEcharts";
    }

    @RequestMapping("/peopleEchart")
    public String peopleEchart(){
        return "/echarts/peopleEcharts";
    }


    @RequestMapping("/getAllClassScore")
    @ResponseBody
    public Map<String,Object> getAllClassScore(String lnu) throws Exception {
        Map<String, Object> map = new HashMap<>();
        if (lnu != null){
            System.out.println("基础课程id为:"+lnu);
            LessonVo lessonVo = echartService.getLessonVoByLnu(lnu);
            String coursename = lessonVo.getLname();
            int totalPass = echartService.getToalPass(lnu);
            int totalNoPass = echartService.getToalNOPass(lnu);
            if(totalPass != 0 || totalNoPass != 0 ){
                map.put("lname",coursename);
                map.put("totalPass",totalPass);
                map.put("totalNoPass",totalNoPass);
                System.out.println("通过人数:"+totalPass);
                System.out.println("未通过人数:"+totalNoPass);
                System.out.println("lname:"+coursename);
            }else {
                map.put("lname",coursename);
                map.put("totalPass",0);
                map.put("totalNoPass",0);

                System.out.println("通过人数:"+totalPass);
                System.out.println("未通过人数:"+totalNoPass);
            }
        }
        return map;
    }

    /***
     * 用于整理学生中的性别比例
     * @return 学生中的男女数量----前端map
     */
    @RequestMapping("/getAllSex")
    @ResponseBody
    public Map<String,Object> getAllSex(){
        Map<String, Object> map = new HashMap<>();
        int totalMan = echartService.getStuentBySex("男");
        int totalWoman = echartService.getStuentBySex("女");
        map.put("totalMan",totalMan);
        map.put("totalWoman",totalWoman);
        map.put("code",0);
        map.put("msg","我是返回的内容");
        return map;
    }

    /**
     * 用于整理学生和教师比例
     * @return 返回学生数和教师数----前端map
     */
    @RequestMapping("/getAllStuAndTea")
    @ResponseBody
    public Map<String,Object> getAllStuAndTea(){
        Map<String, Object> map = new HashMap<>();
        int totalStu = echartService.getStudentCount();
        int totalTea = echartService.getStudentCount();
        map.put("totalStu",totalStu);
        map.put("totalTea",totalTea);
        map.put("code",0);
        map.put("msg","我是返回的内容");
        return map;
    }
}
