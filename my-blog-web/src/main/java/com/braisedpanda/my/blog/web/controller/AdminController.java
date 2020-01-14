package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.ResponseStatus;
import com.braisedpanda.my.blog.commons.model.po.*;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.web.config.redis.RedisUtils;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.braisedpanda.my.blog.web.service.DiaryService;
import com.braisedpanda.my.blog.web.service.EditService;
import com.braisedpanda.my.blog.web.service.GlobalLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private GlobalLogService logService;
    @Autowired
    private RedisUtils redisUtils;

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


    @PostMapping("/diary/insert")
    @ApiOperation("创建随笔")
    public ModelAndView inertDiary(Diary diary, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String createTime = DateUtils.currentStandardDate2();
        diary.setCreateTime(createTime);
        User user = (User)session.getAttribute("user");
        String username = "测试";
        if(user != null){
            username = user.getUsername();
        }
        diary.setUsername(username);
        diaryService.insert(diary);
        ResponseStatus.success("创建随笔成功");
        modelAndView.addObject("message","创建成功");
        modelAndView.setViewName("message");
        return modelAndView;
    }

    @GetMapping("/diary/edit/{id}")
    @ApiOperation("编辑随笔(回显)")
    public ModelAndView toEditDiary(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Diary diary = diaryService.selectByPrimaryKey(id);
        modelAndView.addObject("diary",diary);
        modelAndView.setViewName("diary/edit");
        return modelAndView;
    }

    @PostMapping("/diary/update")
    @ApiOperation("更新随笔")
    public ModelAndView updateDiary(Diary diary){
        ModelAndView modelAndView = new ModelAndView();
        Integer id = diary.getId();
        diaryService.updateByPrimaryKey(diary);
        List<Diary> diaryList = new ArrayList<>();
        diaryList.add(diary);
        modelAndView.addObject("diaryList",diaryList);
        modelAndView.addObject("id",id);
        modelAndView.setViewName("diary/detail");
        return modelAndView;

    }

    @GetMapping("/diary/newDiary")
    @ApiOperation("跳转到创建随笔")
    public ModelAndView toInsert(Model model){
        String newDiaryToken = redisUtils.getToken();
        model.addAttribute("newDiaryToken",newDiaryToken);
        return new ModelAndView("diary/insert");
    }

    @DeleteMapping("/diary/delete/{id}")
    @ApiOperation("删除随笔")
    public void deleteDiary(@PathVariable("id") Integer id){
        diaryService.deleteByPrimaryKey(id);
    }

    @PostMapping("/log/allLog")
    @ApiOperation("查看所有日志")
    public ResponseStatus allLog(){
        List<GlobalLog> globalLogList = logService.selectAll();
        return ResponseStatus.success(globalLogList,"查找所有日志");
    }

    @GetMapping("/log/toallLog")
    @ApiOperation("跳转到日志页面")
    public ModelAndView toLog(){
        return new ModelAndView("log/log");
    }

}
