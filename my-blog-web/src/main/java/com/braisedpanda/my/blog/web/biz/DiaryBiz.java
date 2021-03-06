package com.braisedpanda.my.blog.web.biz;

import com.braisedpanda.my.blog.commons.model.dto.DiaryDto;
import com.braisedpanda.my.blog.commons.utils.FontUtils;
import com.braisedpanda.my.blog.web.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-31 15:52
 **/
@Service
public class DiaryBiz {
    @Autowired
    private DiaryService diaryService;


    public List<DiaryDto> pageDiaryDtoList() {
        List<DiaryDto> diaryDtoList = diaryService.pageDiaryDto();
        for (DiaryDto diaryDto:
             diaryDtoList) {
            String content  =diaryDto.getContent();
            content = FontUtils.HTMLChange(content);
            if(content.length()<=50){
                content = content + "...";
            }else{
                content = content.substring(0,50)+"...";
            }

            diaryDto.setContent(content);
        }
        return diaryDtoList;
    }
}
