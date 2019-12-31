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


    @GetMapping("/newDiary")
    @ApiOperation("跳转到创建随笔")
    public ModelAndView toInsert(){
        return new ModelAndView("diary/insert");
    }

    @PostMapping("/insert")
    @ApiOperation("创建随笔")
    public ModelAndView inertDiary(Diary diary, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        String createTime = DateUtils.currentStandardDate2();
        diary.setCreateTime(createTime);
        User user = (User)session.getAttribute("user");
        if(user != null){
            String  username = user.getUsername();
        }
        String username = "测试";
        diary.setUsername(username);
        diaryService.insert(diary);
        ResponseStatus.success("创建随笔成功");
        modelAndView.addObject("message","创建成功");
        modelAndView.setViewName("message");
        return modelAndView;
    }

    @GetMapping("/allDiary")
    @ApiOperation("跳转到所有随笔")
    public ModelAndView toAllDiary(){
        ModelAndView modelAndView = new ModelAndView();
        List<Diary> diaryList = diaryService.pageDiary();
        for (Diary diary :
                diaryList) {
            String content = diary.getContent();
            content = FontUtils.DBChange(content);
            diary.setContent(content);
        }
        modelAndView.addObject("diaryList",diaryList);
        modelAndView.setViewName("diary/all-diary");
        return modelAndView;
    }

}
