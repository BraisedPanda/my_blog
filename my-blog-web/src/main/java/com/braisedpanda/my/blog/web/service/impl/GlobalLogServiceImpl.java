package com.braisedpanda.my.blog.web.service.impl;

import com.braisedpanda.my.blog.commons.model.po.GlobalLog;
import com.braisedpanda.my.blog.web.mapper.GlobalLogMapper;
import com.braisedpanda.my.blog.web.service.GlobalLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2020-01-07 15:30
 **/
@Service
public class GlobalLogServiceImpl implements GlobalLogService{
    @Autowired
    private GlobalLogMapper globalLogMapper;

    @Override
    public void insert(GlobalLog globalLog) {
        globalLogMapper.insert(globalLog);
    }

    @Override
    public List<GlobalLog> selectAll() {
        return globalLogMapper.selectAll();
    }
}
