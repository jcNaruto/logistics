package com.jiangyue.controller;

import com.jiangyue.common.AbstractUserAuthorizationType;
import com.jiangyue.common.Result;
import com.jiangyue.common.StatusCode;
import com.jiangyue.common.UserRoleEnum;
import com.jiangyue.dto.UserTO;
import com.jiangyue.entity.User;
import com.jiangyue.service.IUserService;
import com.jiangyue.util.JwtUtil;
import com.jiangyue.util.UserAuthorizationUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * create by jiacheng on 2019/12/19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private IUserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PutMapping("/inituser/{userId}")
    @ResponseBody
    public Result regitory(@PathVariable int userId, HttpServletRequest request){
        int statusCode = UserAuthorizationUtil.userAuthorization(request, AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);
        if(statusCode != StatusCode.OK){
            return new Result(false, statusCode,"访问出错");
        }

        userService.levelUpInitUser(userId);
        return new Result(true, StatusCode.OK, "授权成功");
    }

    @PostMapping("/login")
    @ResponseBody
    public Result login(@RequestBody User user, HttpSession session){
        if(StringUtils.isBlank(user.getUserName()) || StringUtils.isBlank(user.getPassword())){
            return new Result(false, StatusCode.ERROR,"用户名或密码为空");
        }
        User loginUser = userService.login(user);
        //session.setAttribute(UserAuth.USER_AUTH, loginUser);
        String jwt = jwtUtil.createJWT(loginUser.getUserId(), loginUser.getUserName(), UserRoleEnum.getValue(loginUser.getRole()));
        return new Result(true, StatusCode.OK,"登陆成功", new UserTO(jwt, loginUser.getUserName()));
    }

}
