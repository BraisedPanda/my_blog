package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.braisedpanda.my.blog.web.service.EditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-20 15:41
 **/
@Api(tags = "页面跳转类", description = "主页面跳转和数据渲染")
@RestController
public class PageController {
    @Autowired
    private BlogPreviewService blogPreviewService;
    @Autowired
    private EditService editService;

    @ApiOperation("首页渲染，查找3篇置顶的博客，和3篇最新的博客")
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        //3篇置顶的博客
        List<BlogPreview> topBlogPreview = blogPreviewService.getTopBlogPreview();
        modelAndView.addObject("topPreviewList",topBlogPreview);
        //3篇最新的博客
        List<BlogPreview> leatestBlogPreview = blogPreviewService.getleatestBlogPreview();
        modelAndView.addObject("leatestPreviewList",leatestBlogPreview);
        modelAndView.setViewName("main");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping("/admin/view")
    public String test(){
        return "123123";
    }
}
