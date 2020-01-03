package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.ResponseStatus;
import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.User;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.commons.utils.FontUtils;
import com.braisedpanda.my.blog.commons.utils.ParamValidator;
import com.braisedpanda.my.blog.web.service.DiaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 11:46
 **/
@RestController
@RequestMapping("/diary")
@Api(tags = "随笔类",description = "描写")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;






    @GetMapping("/allDiary")
    @ApiOperation("跳转到所有随笔")
    public ModelAndView toAllDiary(){
        ModelAndView modelAndView = new ModelAndView();
        List<Diary> diaryList = diaryService.pageDiary();
        for (Diary diary :
                diaryList) {
            String content = diary.getContent();
            content = FontUtils.HTMLChange(content);
            diary.setContent(content);
        }
        modelAndView.addObject("diaryList",diaryList);
        modelAndView.setViewName("diary/all-diary");

        return modelAndView;
    }

    @GetMapping("/view/{id}")
    @ApiOperation("查看某一个随笔详情")
    public ModelAndView viewDiary(@PathVariable("id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        List<Diary> diaryList = new ArrayList<>();
        Diary diary = diaryService.selectByPrimaryKey(id);
        String content = diary.getContent();
        content = FontUtils.HTMLChange(content);
        diary.setContent(content);
        diaryList.add(diary);
        modelAndView.addObject("diaryList",diaryList);
        modelAndView.setViewName("diary/detail");
        return modelAndView;
    }



}
