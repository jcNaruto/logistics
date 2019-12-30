package com.jiangyue.service;

import com.jiangyue.entity.User;

import java.util.List;

/**
 * create by jiacheng on 2019/12/18
 */
public interface IUserService {

    public void registory(User user);

    public User login(User user);

    public void levelUpInitUser(int userId);

    public List<User> getAllList();
}

