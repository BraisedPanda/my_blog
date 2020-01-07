package com.braisedpanda.my.blog.web.service;

import com.braisedpanda.my.blog.commons.model.po.GlobalLog;

import java.util.List;

public interface GlobalLogService {
    void insert(GlobalLog globalLog);

    List<GlobalLog> selectAll();
}
