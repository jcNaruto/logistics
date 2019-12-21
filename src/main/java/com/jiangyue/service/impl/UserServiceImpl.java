package com.jiangyue.service.impl;

import com.jiangyue.dao.IUserRepository;
import com.jiangyue.entity.User;
import com.jiangyue.exception.LogisticsException;
import com.jiangyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * create by jiacheng on 2019/12/18
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void registory(User user) {
        User existUser = userRepository.findByUserName(user.getUserName());
        if(existUser != null){
           throw new LogisticsException("该用户名已存在");
        }
        //强hash加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);

    }

    @Override
    public User login(User user) {
        User realUser = userRepository.findByUserName(user.getUserName());
        if(realUser == null ){
            throw new LogisticsException("用户名不存在");
        }
        if (!bCryptPasswordEncoder.matches(user.getPassword(),realUser.getPassword())) {
            throw new LogisticsException("用户密码不正确");
        }

        return realUser;
    }
}
