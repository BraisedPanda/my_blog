package com.braisedpanda.my.blog.web.service.impl;

import com.braisedpanda.my.blog.commons.model.Editor;
import com.braisedpanda.my.blog.web.mapper.EditMapper;
import com.braisedpanda.my.blog.web.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-23 17:18
 **/
@Service
public class EditServiceImpl implements EditService{
    @Autowired
    private EditMapper editMapper;
    @Override
    public void insert(Editor editor) {
        editMapper.insert(editor);
    }
}
