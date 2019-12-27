package com.braisedpanda.my.blog.web.service;

import com.braisedpanda.my.blog.commons.model.po.Role;
import com.braisedpanda.my.blog.commons.model.po.User;

public interface UserService {
    User getUser(String username, String password);

    Role getRole(int id);
}
