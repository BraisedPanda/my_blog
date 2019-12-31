package com.braisedpanda.my.blog.web.service;

import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.DiaryDto;

import java.util.List;

public interface DiaryService {
    void insert(Diary diary);


    List<DiaryDto> pageDiaryDto();

    List<Diary> pageDiary();
}
