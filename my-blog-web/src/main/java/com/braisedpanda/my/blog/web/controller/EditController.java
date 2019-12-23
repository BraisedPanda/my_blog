package com.braisedpanda.my.blog.web.controller;

import com.braisedpanda.my.blog.commons.model.Editor;
import com.braisedpanda.my.blog.commons.utils.DateUtils;
import com.braisedpanda.my.blog.web.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: my-blog
 * @description: editor.md控制类
 * @author: chenzhen
 * @create: 2019-12-23 16:45
 **/
@RestController
public class EditController {
    @Autowired
    private EditService editService;


    @PostMapping("/editorWeb")
    public void editorWeb(Editor editor){
        String createDate = DateUtils.currentStandardDate();
        editor.setCreate_time(createDate);
        editService.insert(editor);

    }

}
