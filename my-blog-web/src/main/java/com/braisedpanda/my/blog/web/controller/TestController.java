package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.Role;
import com.braisedpanda.my.blog.web.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2020-01-03 15:19
 **/
@RestController
public class TestController {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private RedisTemplate redisTemplate;
    @ResponseBody
    @RequestMapping("/testSql1")
    public List<Diary> testSql1(){
        List<Diary> diaryList = diaryService.testSql1();
        return diaryList;
    }
    @ResponseBody
    @RequestMapping("/testSql2")
    public List<Diary> testSql2(){
        List<Diary> diaryList = diaryService.testSql2();
        redisTemplate.opsForValue().set("1",diaryList);
        List<Diary> list = (List<Diary>) redisTemplate.opsForValue().get("1");

        System.out.println(list);
        return diaryList;
    }


}
