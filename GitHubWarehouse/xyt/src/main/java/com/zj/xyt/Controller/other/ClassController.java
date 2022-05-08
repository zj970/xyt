package com.zj.xyt.Controller.other;

import com.zj.xyt.Entity.Classes;
import com.zj.xyt.Server.ClassService;
import com.zj.xyt.Server.Impl.ClassServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping("/search")
    @ResponseBody
    public List<Classes> search() throws Exception{
        return classService.getAll();
    }
}
