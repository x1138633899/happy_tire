package com.happiness.happy.tire.util;

import com.happiness.happy.tire.entity.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private static final String secretKey = "d48ee2cbdf2ada3f";
    private static final long timeOut = 1 * 60 * 1000;


    /**
     * 创建jwt令牌
     *
     * @param user
     * @return
     */
    public static String createToken(UserInfo user) {
        return Jwts.builder().setId(UUID.randomUUID().toString())
                .setSubject(user.getUserId())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + timeOut))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                /**
                 * token添加自定义属性
                 */
                .claim("name", user.getName())
                .claim("userId", user.getUserId())
                .compact();
    }

    /**
     * 解析jwt令牌
     *
     * @param token
     * @return
     */
    public static Claims parserToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody();
        return claims;
    }

    /**
     * 验证jwt令牌
     *
     * @param token
     * @return
     */
    public static boolean verifyToken(String token) {
        try {
            parserToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
