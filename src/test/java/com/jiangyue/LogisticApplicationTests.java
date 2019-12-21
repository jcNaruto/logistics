package com.jiangyue;

import com.jiangyue.interceptor.SessionInterceptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LogisticApplicationTests {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Test
    void contextLoads() {
        System.out.println(sessionInterceptor);
    }

}
