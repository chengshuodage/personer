package com.cs.personer.utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jack
 */
@Slf4j
public class JwtTokenUtil {

    private static final Long EXPIRE = 30L;
    public static final String EXPIRE_TIME = "expireTime";
    // 下面方法中的key的值
    public static final String BASE64SECRET = "dadadsadasada";

    /**
     * 解密
     */
    @SuppressWarnings("unchecked")
    public static Map parseJWT(String jsonWebToken, String key) {
        Claims claims = Jwts.parser()
                .setSigningKey(key.getBytes())
                .parseClaimsJws(jsonWebToken).getBody();
        return JSON.toJavaObject((JSON) JSON.toJSON(claims), Map.class);
    }

    /**
     * 加密
     */
    public static String createJWT(Map<String, Object> map, String key) {
        map.put(EXPIRE_TIME, LocalDateTime.now().plusMinutes(JwtTokenUtil.EXPIRE));
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //添加构成JWT的参数
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
                .setPayload(JSON.toJSONString(map))
                .signWith(signatureAlgorithm, key.getBytes());
        return builder.compact();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "898765");
        map.put("id", 1);
        map.put("appkey", "HMu1H/cmyKDOiHv41Y9lLROuOlOo+PPG8F4/RotRmNc=");
        map.put("expireTime", LocalDateTime.now().plusMinutes(JwtTokenUtil.EXPIRE));

        //密钥
        String keyt = "79e7c69681b8270162386e6daa53d1dc";
        String token = JwtTokenUtil.createJWT(map, keyt);
        System.out.println("JWT加密的结果：" + token);
        System.out.println(JwtTokenUtil.parseJWT(token, keyt));
    }
}
