package com.braisedpanda.my.blog.web.service;

import com.braisedpanda.my.blog.commons.model.po.BlogPreview;

import java.util.List;

public interface BlogPreviewService {
    void insertBlogPreview(BlogPreview blogPreview);

    List<BlogPreview> getTopBlogPreview();

    List<BlogPreview> getleatestBlogPreview();
}
