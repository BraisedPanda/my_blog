package com.braisedpanda.my.blog.web.mapper;

import com.braisedpanda.my.blog.commons.model.po.Diary;
import com.braisedpanda.my.blog.commons.model.po.DiaryDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface DiaryMapper extends tk.mybatis.mapper.common.Mapper<Diary>{
    List<DiaryDto> selectAllDiaryDto();
}
