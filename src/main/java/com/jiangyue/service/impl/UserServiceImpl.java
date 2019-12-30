package com.jiangyue.service.impl;

import com.jiangyue.common.UserRoleEnum;
import com.jiangyue.dao.IUserRepository;
import com.jiangyue.entity.User;
import com.jiangyue.exception.LogisticsException;
import com.jiangyue.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

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
        user.setRole(UserRoleEnum.INIT_USER_ROLE.getSeq());
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());
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

    @Override
    public void levelUpInitUser(int userId) {
        User user =  checkUser(userId);
        user.setRole(UserRoleEnum.USER_ROLE.getSeq());

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllList() {
        Sort sort = Sort.by(Sort.Direction.DESC, "role");
        return userRepository.findAll(sort);
    }

    /**
     * @Author zhaojiacheng
     * @Description  check Warehouse 是否存在
     * @Date 21:47
     */
    private User checkUser(Integer userId){
        User user = null;
        try {
            user = userRepository.findById(userId).get();
        } catch (NoSuchElementException e) {
            throw new LogisticsException(e, "userId" + userId + "不存在!");
        }
        return user;
    }
}
