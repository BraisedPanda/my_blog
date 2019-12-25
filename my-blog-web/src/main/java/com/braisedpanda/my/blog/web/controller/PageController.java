package com.braisedpanda.my.blog.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-20 15:41
 **/
@RestController
public class PageController {

    @RequestMapping("index")
    public ModelAndView index(){
        return new ModelAndView("mian");
    }

    @RequestMapping("/test")
    public ModelAndView test(){
        return new ModelAndView("/blog/edit");
    }
}
