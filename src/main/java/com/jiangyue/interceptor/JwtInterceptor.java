package com.jiangyue.interceptor;


import com.jiangyue.common.AbstractUserAuthorizationType;
import com.jiangyue.common.UserRoleEnum;
import com.jiangyue.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;
    //客户端传来的token，约定在加头信息Authorization ,内容为Bearer+空格+Token
    private final String TOKEN_AUTHORIZATION = "Authorization";
    private final String TOKEN_PERFIX = "Bearer ";
    private final String ROLES_KEY = "roles";

    /**
     * @Author
     * @Description  再次过滤器解析客户端传来的token，约定在加头信息Authorization ,内容为Bearer+空格+Token
     * @Date 13:29 2019/3/6
     * @Param [request, response, handler]
     * @throws
     * @return true 全部放行，到具体的方法在具体校验
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String authHeader = request.getHeader(TOKEN_AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(TOKEN_PERFIX)) {
            final String token = authHeader.substring(7); // The part after "Bearer "
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if(UserRoleEnum.ADMIN_ROLE.getDesc().equals(claims.get(ROLES_KEY))){//如果是管理员
                    request.setAttribute(AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY, claims);
                }
                if(UserRoleEnum.USER_ROLE.getDesc().equals(claims.get(ROLES_KEY))){//如果是用户
                    request.setAttribute(AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY, claims);
                }
                if(UserRoleEnum.INIT_USER_ROLE.getDesc().equals(claims.get(ROLES_KEY))){//如果是用户
                    request.setAttribute(AbstractUserAuthorizationType.INIT_AUTHORIZATION_KEY, claims);
                }

            }
        }
        return true;
    }

}