package com.jiangyue.interceptor;

import com.alibaba.fastjson.JSON;
import com.jiangyue.common.Result;
import com.jiangyue.common.StatusCode;
import com.jiangyue.common.UserAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * create by jiacheng on 2019/12/19
 */
@Component
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("===========开始拦截============");
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(UserAuth.USER_AUTH) != null) {
            return true;
        } else {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            PrintWriter out = null;
            Result result = new Result(false, StatusCode.NOSESSION,"用户未登陆");
            out = response.getWriter();
            out.append(JSON.toJSONString(result));
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

