package com.braisedpanda.my.blog.web.mapper;

import com.braisedpanda.my.blog.commons.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends tk.mybatis.mapper.common.Mapper<User>{
}
