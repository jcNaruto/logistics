package com.jiangyue.util;

import com.jiangyue.exception.LogisticsException;
import io.jsonwebtoken.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Component
@ConfigurationProperties("jwt.config")
public class JwtUtil {

    private final String TOKEN_AUTHORIZATION = "Authorization";

    private String key ;

    private long ttl ;//一个小时

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTtl() {
        return ttl;
    }

    public void setTtl(long ttl) {
        this.ttl = ttl;
    }


    /**
     * @Author
     * @Description 生成 JWT
     * @Date 13:39 2019/3/6
     * @Param [id, subject, roles]
     * @throws
     * @return java.lang.String
     */
    public String createJWT(Integer id, String subject, String roles) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder().setId(id.toString())
                .setSubject(subject)
                .setIssuedAt(now)
                .signWith(SignatureAlgorithm.HS256, key).claim("roles", roles);
        if (ttl > 0) {
            builder.setExpiration( new Date( nowMillis + ttl));
        }
        return builder.compact();
    }


    /**
     * @Author
     * @Description 解析jwt
     * @Date 13:40 2019/3/6
     * @Param [jwtStr]
     * @throws
     * @return io.jsonwebtoken.Claims
     */
    public Claims parseJWT(String jwtStr){
        try {
            return  Jwts.parser()
                    .setSigningKey(key)
                    .parseClaimsJws(jwtStr)
                    .getBody();
        } catch (Exception e) {
            throw new LogisticsException("请登陆");
        }
    }

    /**
     * @Author
     * @Description 根据token获取用户id,传入request
     * @Date 22:39 2019/3/6
     * @Param [request]
     * @throws
     * @return java.lang.String
     */
    public  String parseJWTGetUserId(HttpServletRequest request){
        String token = request.getHeader(TOKEN_AUTHORIZATION).substring(7);
        return  Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody().getId();
    }


}
