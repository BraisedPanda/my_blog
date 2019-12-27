package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.ResponseStatus;
import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.commons.model.po.Editor;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.braisedpanda.my.blog.web.service.EditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


/**
 * @program: my-blog
 * @description: editor.md控制类
 * @author: chenzhen
 * @create: 2019-12-23 16:45
 **/
@Api(tags = "markdown类",description = "插入，编辑，查看markdown")
@RequestMapping("/blog")
@RestController
public class EditController {
    @Autowired
    private EditService editService;
    @Autowired
    private BlogPreviewService blogpreviewService;



    @ApiOperation("查看博客")
    @GetMapping("/view/{id}")
    public ModelAndView preview(@PathVariable(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        //view数加1
        BlogPreview blogPreview = blogpreviewService.getBlogPreviewById(id);
        int views = blogPreview.getViews()+1;
        blogPreview.setViews(views);
        blogpreviewService.updateBlogPreview(blogPreview);
        //显示博客内容
        Editor editor = editService.findEditByBlogId(id);
        modelAndView.addObject("id",id);
        modelAndView.addObject("editor",editor);
        modelAndView.setViewName("blog/preview");
        return modelAndView;
    }

    @ApiOperation("查看全部博客")
    @GetMapping("/allblog/{page}")
    public ModelAndView allBlog(@PathVariable(value = "page") int page) {
        ModelAndView modelAndView = new ModelAndView();

        List<BlogPreview> topBlogPreview = blogpreviewService.getAllBlogPreview(page);
        modelAndView.addObject("topPreviewList",topBlogPreview);

        modelAndView.setViewName("/blog/all-blog");
        return modelAndView;
    }







}
