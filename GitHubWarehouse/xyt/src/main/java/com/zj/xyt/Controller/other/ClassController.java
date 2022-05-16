package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.Classes;
import com.zj.xyt.Server.ClassService;
import com.zj.xyt.Server.Impl.ClassServiceImpl;
import com.zj.xyt.utils.PageUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Api(tags = "班级接口")
@RequestMapping("/class")
public class ClassController {
    @Autowired
    ClassService classService;

    @RequestMapping("/index")
    public String index() throws Exception {
        return "/system/class/index";
    }
    @RequestMapping("/search")
    @ResponseBody
    public List<Classes> search() throws Exception{
        return classService.getAll();
    }
    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> list(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit,
                                   Classes classes) throws Exception{
        Map<String,Object> map = new HashMap<>();
        int count = classService.getCount();
//        System.out.println("总行数:"+count);
        PageUtil pageUtil = new  PageUtil(page,limit);
        List<Classes> list = classService.getList(classes,pageUtil);

        map.put("count",count);
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");

        return map;
    }

    @RequestMapping("/classForm")
    public String classForm() throws Exception {
        return "system/class/classForm";
    }

    @RequestMapping(value = "/addClass",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addClass(Classes classes) throws Exception{
        Map<String,Object> map = new HashMap<>();

        List<Classes> list = classService.findClass(classes.getCnu());

        if (list.size() != 0){
            map.put("msg","班级已存在");
            map.put("result",false);
        }else if(classes.getCnu().length() <= 0){
            map.put("msg","班级不能为空");
            map.put("result",false);
        }else{
            //课程为null也可以添加 待完善
            classService.addClass(classes);
            map.put("msg","添加成功");
            map.put("result",true);
        }
        return map;
    }

    @RequestMapping("/batchDeleteClass")
    @ResponseBody
    @RequiresPermissions("class:delete")
    public Map<String, Object> batchDeleteClass(String[] ids) throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        classService.batchDeleteClass(ids);
        map.put("msg","删除成功");
        map.put("result",true);
        return map;
    }

    @RequestMapping(value = "/getClassView")
    @ResponseBody
    public Classes getClassView(String cnu) throws Exception {
        return classService.getClassView(cnu);
    }

    @RequestMapping(value = "/editClass",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> editClass(Classes classes) throws Exception{
        Map<String, Object> map = new HashMap<>();

        classService.updateClass(classes);

        map.put("result",true);
        return map;
    }

}
