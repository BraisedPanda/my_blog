package com.braisedpanda.my.blog.web.service.impl;

import com.braisedpanda.my.blog.commons.model.po.BlogPreview;
import com.braisedpanda.my.blog.web.mapper.BlogPreviewMapper;
import com.braisedpanda.my.blog.web.service.BlogPreviewService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

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

    /**
    * @Description: 获取置顶的3篇博客
    * @Param: []
    * @Date: 2019/12/25 0025
    */
    @Override
    public List<BlogPreview> getTopBlogPreview() {
        Example example = new Example(BlogPreview.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("top",1);
        example.setOrderByClause("create_time desc");
        List<BlogPreview> list = blogPreviewMapper.selectByExample(example);
        return list;
    }


    /**
     * @Description: 获取置顶的3篇博客
     * @Param: []
     * @Date: 2019/12/25 0025
     */
    @Override

    public List<BlogPreview> getleatestBlogPreview() {
        PageHelper.startPage(1,7);
        Example example = new Example(BlogPreview.class);
        example.setOrderByClause("create_time desc");
        List<BlogPreview> list = blogPreviewMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);

        return pageInfo.getList();
    }

}
