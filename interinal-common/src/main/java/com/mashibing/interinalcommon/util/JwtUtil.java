package com.mashibing.interinalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mashibing.interinalcommon.constant.dto.TokenResult;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    // 盐
    private static final String SIGN = "CPE!@#";

    private static final String JWT_KEY = "phone";

    //
    private static final String JWT_KEY_IDENTITY = "identity";

    public static String genertorToken(String passengerPhone,String identity){
        HashMap<String, String> map = new HashMap<>();
        map.put(JWT_KEY, passengerPhone);
        map.put(JWT_KEY_IDENTITY, identity);
        // 设置 token 过期时间 1 天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,1);
        Date date = calendar.getTime();
        JWTCreator.Builder builder = JWT.create();

        // 将 token 进行遍历 方到 map 中
        map.forEach(
                (k,v) ->{
                    builder.withClaim(k,v);
                }
        );

        // 整合 过期时间
        builder.withExpiresAt(date);

        // 生成 token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));



        return sign;
    }

    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

        String phone = verify.getClaim(JWT_KEY).toString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).toString();
        TokenResult tokenResult = new TokenResult(phone,identity);
        return tokenResult;
    }

    public static void main(String[] args) {

        // 传入 号码，返回token
        String token = genertorToken("18822100030","1");
        System.out.println("token = " + token);
        // 将token 解析 生成原数据
        TokenResult tokenResult = parseToken(token);
        System.out.println("手机 = " + tokenResult.getPhone()+";身份 =" +tokenResult.getIdentity());

    }
}
