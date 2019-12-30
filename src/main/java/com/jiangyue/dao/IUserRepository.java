package com.jiangyue.dao;

import com.jiangyue.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * create by jiacheng on 2019/12/18
 */
public interface IUserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);


}
