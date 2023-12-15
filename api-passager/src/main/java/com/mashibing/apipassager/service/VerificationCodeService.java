package com.mashibing.apipassager.service;

import com.mashibing.apipassager.remote.ServicePassengerUserClient;
import com.mashibing.apipassager.remote.ServiceVefifcationcodeClient;
import com.mashibing.apipassager.request.TokenResponse;
import com.mashibing.interinalcommon.constant.CommonStatusEnum;
import com.mashibing.interinalcommon.constant.IdentityConstant;
import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import com.mashibing.interinalcommon.constant.dto.TokenResult;
import com.mashibing.interinalcommon.responese.NumberCodeResponse;
import com.mashibing.interinalcommon.responese.VerificationCodeDTO;
import com.mashibing.interinalcommon.util.JwtUtil;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.print.DocFlavor;
import java.util.concurrent.TimeUnit;

@Service
public class VerificationCodeService {

    private String verificationCodePrefix = "passenger-verification-code-";

    /**
     * 调用远程服务 生成 验证码
     */
    @Autowired
    private ServiceVefifcationcodeClient serviceVefifcationcodeClient;

    /**
     * 调用远程服务 存入数据库 手机号
     */
    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult generatorCode(String passengerPhone){
        // 调用验证码服务获取验证码
        System.out.println("调用验证码服务获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeRespons = serviceVefifcationcodeClient.getNumberCode(6);
        // code 验证码
        int code = numberCodeRespons.getData().getNumberCode();
        System.out.println("调用验证码服务获取验证码 code = " + code);



        // 设置 redis key值
        String key = generatorKeyByPhone(passengerPhone);
        System.out.println("存入redis中key"+key);
        //存入 redis
        stringRedisTemplate.opsForValue().set(key,code+"",2, TimeUnit.MINUTES);


        return ResponseResult.success("将验证码存入redis"+code);
    }

    /**
     * 根据 key 生成value
     * @param passengerPhone
     *
     * @return
     */
    public String generatorKeyByPhone(String passengerPhone){
        return verificationCodePrefix+passengerPhone;
    }

    public ResponseResult checkCode(String passengerPhone,String verificationCode){

        System.out.println("获取手机号 = " + passengerPhone+";验证码 = "+verificationCode);

        // 读取redis存入的 验证码

        // 获取 key值
        String key = generatorKeyByPhone(passengerPhone);
        System.out.println("传入生成key"+key);
        //根据 key值获取value 值
        String codeRedis = stringRedisTemplate.opsForValue().get(key);
        System.out.println("获取redis中的value值 = " + codeRedis);
        //校验验证码 - redis中验证码过期
        if(StringUtils.isEmpty(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICTION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICTION_CODE_ERROR.getValue());
        }
        //校验验证码 - redis中验证码不符
        if((codeRedis.trim()).equals(verificationCode.trim())){  // Stirng中的trim()方法的作用就是去掉字符串前面和后面的空格
            return ResponseResult.fail(CommonStatusEnum.VERIFICTION_CODE_ERROR.getCode(),CommonStatusEnum.VERIFICTION_CODE_ERROR.getValue());
        }

        // 判断是否有用户 并进行处理 (对手机信息进行存储)
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        // 颁发 token 令牌
        String token = JwtUtil.genertorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);
        return ResponseResult.success(tokenResponse);
    }
}
