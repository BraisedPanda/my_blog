package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.DiaryDto;
import com.braisedpanda.my.blog.commons.model.po.DiaryWebDto;
import com.braisedpanda.my.blog.commons.utils.FontUtils;
import com.braisedpanda.my.blog.web.biz.DiaryBiz;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.braisedpanda.my.blog.web.service.DiaryService;
import com.braisedpanda.my.blog.web.service.EditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    private DiaryBiz diaryBiz;
    @Autowired
    private DiaryService diaryService;

    @ApiOperation("首页渲染，查找3篇置顶的博客，和3篇最新的博客，6篇随笔")
    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        //3篇置顶的博客
        List<BlogPreview> topBlogPreview = blogPreviewService.getTopBlogPreview();
        modelAndView.addObject("topPreviewList",topBlogPreview);
        //3篇最新的博客
        List<BlogPreview> leatestBlogPreview = blogPreviewService.getleatestBlogPreview();
        modelAndView.addObject("leatestPreviewList",leatestBlogPreview);
        List<DiaryDto> diaryDtoList = diaryBiz.pageDiaryDtoList();
        modelAndView.addObject("diaryDtoList",diaryDtoList);
        modelAndView.setViewName("main");
        return modelAndView;
    }


    @ApiOperation("查看关于网站的页面")
    @GetMapping("/about/web")
    public ModelAndView aboutWeb(){
        ModelAndView modelAndView = new ModelAndView();
        List<Diary> diaryList = diaryService.selectDirayWeb();
        List<DiaryWebDto> diaryWebDtoList = new ArrayList<>();

        for (Diary diary :
                diaryList) {
            DiaryWebDto diaryWebDto = new DiaryWebDto();
            String content = diary.getContent();
            content = FontUtils.HTMLChange(content);
            diaryWebDto.setContent(content);
            diaryWebDto.setId(diary.getId());
            diaryWebDto.setTitle(diary.getTitle());
            String createTime = diary.getCreateTime();
            String[] time = createTime.split("-");
            diaryWebDto.setYear(time[0]);
            diaryWebDto.setMonth(time[1]);
            diaryWebDto.setDay(time[2]);
            diaryWebDtoList.add(diaryWebDto);
        }
        modelAndView.addObject("diaryList",diaryWebDtoList);

        modelAndView.setViewName("about/web");
        return modelAndView;
    }
}
