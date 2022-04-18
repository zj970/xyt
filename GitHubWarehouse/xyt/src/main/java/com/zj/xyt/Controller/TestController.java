package com.zj.xyt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    @RequestMapping("/homePage")
    public ModelAndView homePage(){
        ModelAndView mv = new ModelAndView("system/home/homePage");
        return mv;
    }

    @RequestMapping("/1")
    public ModelAndView success(){
        ModelAndView mv = new ModelAndView("system/home/homePage");
        return mv;
    }
}
