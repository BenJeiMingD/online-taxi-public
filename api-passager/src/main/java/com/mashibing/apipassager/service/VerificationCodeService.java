package com.mashibing.apipassager.service;

import com.mashibing.apipassager.remote.ServiceVefifcationcodeClient;
import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import com.mashibing.interinalcommon.responese.NumberCodeResponse;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVefifcationcodeClient serviceVefifcationcodeClient;

    public String generatorCode(String passengerPhone){
        // 调用验证码服务获取验证码
        System.out.println("调用验证码服务获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeRespons = serviceVefifcationcodeClient.getNumberCode(6);

        int code = numberCodeRespons.getData().getNumberCode();
        System.out.println("调用验证码服务获取验证码 code = " + code);

        System.out.println("存入redis");

        JSONObject result = new JSONObject();
        result.put("code",1);
        result.put("message","success");
        return result.toString();
    }
}
