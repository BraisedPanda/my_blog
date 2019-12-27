package com.braisedpanda.my.blog.web.service.impl;

import com.braisedpanda.my.blog.commons.model.po.Role;
import com.braisedpanda.my.blog.commons.model.po.User;
import com.braisedpanda.my.blog.web.mapper.RoleMapper;
import com.braisedpanda.my.blog.web.mapper.UserMapper;
import com.braisedpanda.my.blog.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


/**
 * @program: my-blog
 * @description:
 * @author: chenzhen
 * @create: 2019-12-27 16:15
 **/
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public User getUser(String username, String password) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        criteria.andEqualTo("password",password);
        User user = userMapper.selectOneByExample(example);
        if(user != null){
            return user;
        }else
            return null;
    }


    @Override
    public Role getRole(int id) {
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",id);
        Role role = roleMapper.selectOneByExample(example);
        return role;
    }
}
