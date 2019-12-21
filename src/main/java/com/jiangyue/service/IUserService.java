package com.jiangyue.service;

import com.jiangyue.entity.User;

/**
 * create by jiacheng on 2019/12/18
 */
public interface IUserService {

    public void registory(User user);

    public User login(User user);

}
