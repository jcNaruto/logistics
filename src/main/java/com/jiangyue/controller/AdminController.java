package com.jiangyue.controller;

import com.jiangyue.common.AbstractUserAuthorizationType;
import com.jiangyue.common.Result;
import com.jiangyue.common.StatusCode;
import com.jiangyue.common.UserRoleEnum;
import com.jiangyue.dto.UserTO;
import com.jiangyue.entity.User;
import com.jiangyue.service.IUserService;
import com.jiangyue.util.JwtUtil;
import com.jiangyue.util.TransformResultCodeUtil;
import com.jiangyue.util.UserAuthorizationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * create by jiacheng on 2019/12/19
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IUserService userService;

    @PutMapping("/inituser/{userId}")
    @ResponseBody
    public Result regitory(@PathVariable int userId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }

        userService.levelUpInitUser(userId);
        return new Result(true, StatusCode.OK, "授权成功");
    }

    @GetMapping("/")
    @ResponseBody
    public Result getAll(HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return TransformResultCodeUtil.trans(statusCode);
        }
        return new Result(true, StatusCode.OK, "查询成功", userService.getAllList());
    }



}
