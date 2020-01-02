package com.braisedpanda.my.blog.web.service.impl;


import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.DiaryDto;
import com.braisedpanda.my.blog.web.mapper.DiaryMapper;
import com.braisedpanda.my.blog.web.service.DiaryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        PageHelper.startPage(1,6);
        List<DiaryDto> diaryDtoList = diaryMapper.selectAllDiaryDto();
        PageInfo pageInfo = new PageInfo(diaryDtoList);
        return pageInfo.getList();
    }

    @Override
    public List<Diary> pageDiary() {
        PageHelper.startPage(1,10);
        Example example = new Example(Diary.class);
        example.setOrderByClause("createTime desc");
        List<Diary> diaryList = diaryMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(diaryList);
        return pageInfo.getList();
    }

    @Override
    public Diary selectByPrimaryKey(Integer id) {
        Diary diary = diaryMapper.selectByPrimaryKey(id);
        return diary;
    }

    @Override
    public void updateByPrimaryKey(Diary diary) {
        diaryMapper.updateByPrimaryKeySelective(diary);

    }

    @Override
    public void deleteByPrimaryKey(Integer id) {
        diaryMapper.deleteByPrimaryKey(id);
    }
}
