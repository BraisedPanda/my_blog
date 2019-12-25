package com.braisedpanda.my.blog.web.service.impl;

import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.web.mapper.BlogPreviewMapper;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-24 11:09
 **/
@Service
public class BlogPreviewServiceImpl implements BlogPreviewService{
    @Autowired
    private BlogPreviewMapper blogPreviewMapper;

    @Override
    public void insertBlogPreview(BlogPreview blogPreview) {
        blogPreviewMapper.insertBlogPreview(blogPreview);
    }
}
