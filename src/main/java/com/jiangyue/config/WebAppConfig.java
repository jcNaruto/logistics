package com.jiangyue.config;

import com.jiangyue.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * create by jiacheng on 2019/12/20
 */
@Configuration
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    /**
     * 注册 拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 设置拦截的路径、不拦截的路径、优先级等等
        InterceptorRegistration addInterceptor = registry.addInterceptor(sessionInterceptor);
        addInterceptor.addPathPatterns("/**")
                .excludePathPatterns("/**/login")
                .excludePathPatterns("/**/registory");
    }
}

