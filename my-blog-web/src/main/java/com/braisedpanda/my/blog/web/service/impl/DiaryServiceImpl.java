package com.braisedpanda.my.blog.web.service.impl;


import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.DiaryDto;
import com.braisedpanda.my.blog.web.mapper.DiaryMapper;
import com.braisedpanda.my.blog.web.service.DiaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 14:32
 **/
@Service
public class DiaryServiceImpl implements DiaryService {
    @Autowired
    private DiaryMapper diaryMapper;

    @Override
    public void insert(Diary diary) {
        diaryMapper.insert(diary);
    }

    @Override
    public List<DiaryDto> pageDiaryDto() {
        PageHelper.startPage(1,10);
        List<DiaryDto> diaryDtoList = diaryMapper.selectAllDiaryDto();
        PageInfo pageInfo = new PageInfo(diaryDtoList);
        return pageInfo.getList();
    }

    @Override
    public List<Diary> pageDiary() {
        PageHelper.startPage(1,10);
        List<Diary> diaryList = diaryMapper.selectAll();
        PageInfo pageInfo = new PageInfo(diaryList);
        return pageInfo.getList();
    }
}
