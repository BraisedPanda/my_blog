package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.ResponseStatus;
import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.commons.model.po.Editor;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.braisedpanda.my.blog.web.service.EditService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @program: my-blog
 * @description: 管理员控制类
 * @author: chenzhen
 * @create: 2019-12-27 16:53
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private EditService editService;
    @Autowired
    private BlogPreviewService blogpreviewService;

    @ApiOperation("插入markdown")
    @PostMapping("/blog/insert")
    public ResponseStatus insertEditor(Editor editor, BlogPreview blogPreview){
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

    @ApiOperation("新建博客")
    @GetMapping("/blog/newBlog")
    public ModelAndView newBlog(){
        return new ModelAndView("blog/insert");
    }

    @ApiOperation("编辑博客(回显)")
    @GetMapping("/blog/edit/{id}")
    public ModelAndView toeditEditor(@PathVariable(value="id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Editor editor = editService.findEditByBlogId(id);
        BlogPreview blogPreview = blogpreviewService.getBlogPreviewById(id);
        modelAndView.addObject("editor",editor);
        modelAndView.addObject("blogPreview",blogPreview);
        modelAndView.setViewName("blog/edit");
        return modelAndView;
    }

    @ApiOperation("编辑博客(存入数据库)")
    @PostMapping("/blog/edit/{id}")
    public ResponseStatus editEditor(Editor editor, BlogPreview blogPreview, Integer editorId, Integer blogId){
        /*首先保存预览对象*/
        blogPreview.setId(blogId);
        editor.setBlogId(blogId);
        editor.setId(editorId);

        blogpreviewService.updateBlogPreview(blogPreview);
        /*保存markdown*/

        editService.updateEditor(editor);

        return  ResponseStatus.success("插入markdown成功");
    }

    @ApiOperation("删除博客")
    @DeleteMapping("/blog/delete/{id}")
    public ResponseStatus deleteBlog(@PathVariable(value="id") Integer id, Integer blogId){
        editService.deleteById(id);
        blogpreviewService.deleteById(blogId);
        return  ResponseStatus.success("插入markdown成功");
    }


}
