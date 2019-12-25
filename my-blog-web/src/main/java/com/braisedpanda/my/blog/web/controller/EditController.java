package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.ResponseStatus;
import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.commons.model.po.Editor;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.braisedpanda.my.blog.web.service.EditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

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
    @ApiOperation("插入markdown")
    @PostMapping("/insert")
    public ResponseStatus editorWeb(Editor editor, BlogPreview blogPreview){
        /*首先保存预览对象*/
        String createDate = DateUtils.currentStandardDate();
        blogPreview.setCreateTime(createDate);
        blogPreview.setViews(0);
        blogPreview.setTop(0);
        blogpreviewService.insertBlogPreview(blogPreview);
         /*保存markdown*/
        int blogId = blogPreview.getId();
        editor.setBlogId(blogId);
        editor.setCreate_time(createDate);
        editService.insert(editor);

        return  ResponseStatus.success("插入markdown成功");
    }


    @ApiOperation("查看博客")
    @RequestMapping("/view/{id}")
    public ModelAndView preview(@PathVariable(value = "id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        Editor editor = editService.findEditById(id);
        modelAndView.addObject("editor",editor);
        modelAndView.setViewName("blog/preview");
        return modelAndView;
    }

}
