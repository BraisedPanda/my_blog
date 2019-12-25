package com.braisedpanda.my.blog.web.service;

import com.braisedpanda.my.blog.commons.model.po.Editor;

public interface EditService {
    void insert(Editor editor);

    Editor findEditById(int id);
}
