package com.jiangyue.common;

/**
 * @ClassName AbstractUserAuthorizationType
 * @Description 描述不同角色所对应的所需要的权限的key,不同的角色对应不同的key,作用是去request中查找是否具有相应的权限，前提是经过
 * JwtInterceptor的统一处理将相应权限的claim传入request中
 * 考虑到以后的角色会增加，使用不能实例化的抽象类的全局常量表示其状态
 * @Author
 * @Date 2019/3/6 13:51
 * @Version 1.0
 */
public abstract class AbstractUserAuthorizationType {

    public static final String USER_AUTHORIZATION_KEY = "user_claims";//普通用户对应的key
    public static final String ADMIN_AUTHORIZATION_KEY = "admin_claims";//admin用户对应的key

}
