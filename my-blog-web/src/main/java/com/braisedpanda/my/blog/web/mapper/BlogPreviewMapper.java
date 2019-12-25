package com.braisedpanda.my.blog.web.mapper;

import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-24 11:02
 **/
@Mapper
public interface BlogPreviewMapper extends tk.mybatis.mapper.common.Mapper<BlogPreview>{
    void insertBlogPreview(BlogPreview blogPreview);
}
