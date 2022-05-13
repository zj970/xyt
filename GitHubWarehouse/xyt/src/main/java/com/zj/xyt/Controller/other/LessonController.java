package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.LessonVo;
import com.zj.xyt.Server.LessonService;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
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
@Api(tags = "课程接口")
@RequestMapping("/baseCourse")
public class LessonController {
    @Autowired
    LessonService lessonService;

    @GetMapping("/index")
    public String index() throws Exception {
        return "/system/baseCourse/index";
    }

    @RequestMapping("/search")
    @ResponseBody
    public List<LessonVo> search() throws Exception{
        return lessonService.queryAll();
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit,
                                   LessonVo lessonVo) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int count = lessonService.getCount();
        PageUtil pageUtil = new PageUtil(page,limit);
        lessonVo.toString();
        List<LessonVo> list = lessonService.getList(lessonVo,pageUtil);
        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        return map;
    }
}
