package com.jiangyue.util;

import com.jiangyue.common.AbstractUserAuthorizationType;
import com.jiangyue.common.StatusCode;
import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserAuthorizationUtil
 * @Description 抽取每个具体的controller层中的权限验证的方法
 * @Author
 * @Date 2019/3/6 21:38
 * @Version 1.0
 */
public class UserAuthorizationUtil {

    /**
     * @Author
     * @Description 高权限的人不具备低权限的角色的功能，低权限的人不具备高权限的资格
     * @Date 22:13 2019/3/6
     * @Param [request, userAuthorizationKey]
     * @throws
     * @return void
     */
    public static int userAuthorization(HttpServletRequest request, String userAuthorizationKey){
        Claims initClaims = (Claims)request.getAttribute(AbstractUserAuthorizationType.INIT_AUTHORIZATION_KEY);
        if(initClaims != null){
            return StatusCode.INITUSER;
        }

        Claims userClaims = (Claims)request.getAttribute(AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY);
        Claims adminClaims = (Claims)request.getAttribute(AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY);
        if(userClaims == null && adminClaims == null){
            return StatusCode.NOSESSION;
        }
        if(AbstractUserAuthorizationType.COMMON_AUTHORIZATION_KEY.equals(userAuthorizationKey)){
            return StatusCode.OK;
        } else if(AbstractUserAuthorizationType.ADMIN_AUTHORIZATION_KEY.equals(userAuthorizationKey)){
            if(adminClaims == null && userClaims != null){
                return StatusCode.NOAUTH;
            } else {
                return StatusCode.OK;
            }
        } else if(AbstractUserAuthorizationType.USER_AUTHORIZATION_KEY.equals(userAuthorizationKey)){
            if(adminClaims != null && userClaims == null){
                return StatusCode.NOAUTH;
            } else {
                return StatusCode.OK;
            }
        }
        return StatusCode.OK;
    }
}
