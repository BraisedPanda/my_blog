package com.braisedpanda.my.blog.web.service.impl;

import com.braisedpanda.my.blog.commons.model.po.Editor;
import com.braisedpanda.my.blog.web.mapper.EditMapper;
import com.braisedpanda.my.blog.web.service.EditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    
    /** 
    * @Description: 根据blog_id查找博客
    */
    @Override
    public Editor findEditById(int id) {
        Example example = new Example(Editor.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("blogId",id);
        Editor editor = editMapper.selectOneByExample(example);
        return editor;
    }
    /** 
    * @Description: 更新
    * @Param: [editor]
    * @Date: 2019/12/27 0027 
    */ 
    @Override
    public void updateEditor(Editor editor) {
        editMapper.updateByPrimaryKeySelective(editor);
    }
    /** 
    * @Description: 根据Id删除博客
    * @Param: [id]
    * @Date: 2019/12/27 0027 
    */ 
    @Override
    public void deleteById(Integer id) {
        editMapper.deleteByPrimaryKey(id);
    }
}
