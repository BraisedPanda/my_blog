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
        PageHelper.startPage(1,3);
        Example example = new Example(BlogPreview.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("top",1);
        example.setOrderByClause("create_time desc");
        List<BlogPreview> list = blogPreviewMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo.getList();
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
    /** 
    * @Description: 根据id查找BlogPreview对象
    * @Param: [id]
    * @Date: 2019/12/27 0027 
    */ 
    @Override
    public BlogPreview getBlogPreviewById(int id) {
        BlogPreview blogPreview = blogPreviewMapper.selectByPrimaryKey(id);
        return blogPreview;
    }
    
    /** 
    * @Description: 更新
    * @Param: [blogPreview]
    * @Date: 2019/12/27 0027 
    */ 
    @Override
    public void updateBlogPreview(BlogPreview blogPreview) {
        blogPreviewMapper.updateByPrimaryKeySelective(blogPreview);
    }
    /** 
    * @Description: 根据ID删除博客预览
    * @Param: [blogId]
    * @Date: 2019/12/27 0027 
    */ 
    @Override
    public void deleteById(Integer blogId) {
        blogPreviewMapper.deleteByPrimaryKey(blogId);
    }
    /** 
    * @Description: 分页查询所有的博客
    * @Param: [page]
    * @Date: 2019/12/27 0027 
    */ 
    @Override
    public List<BlogPreview> getAllBlogPreview(int page) {
        PageHelper.startPage(page,10);
        Example example = new Example(BlogPreview.class);
        example.setOrderByClause("create_time desc");
        List<BlogPreview> list = blogPreviewMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo.getList();
    }
}
