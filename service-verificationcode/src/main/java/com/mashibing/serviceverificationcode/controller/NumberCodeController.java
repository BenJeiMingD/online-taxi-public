package com.mashibing.serviceverificationcode.controller;

import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import com.mashibing.interinalcommon.responese.NumberCodeResponse;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.SourceVersion;

@RestController
public class NumberCodeController {

    @GetMapping("numberCode/{size}")
    public ResponseResult  numberCode (@PathVariable("size") int size){

        System.out.println("size = " + size);

        /**
         * 获取size位数的随机验证码
         *
         * 0.12345;当要4位数的验证码，*10 的3次方 Math.pow(10,size-1)
         * Math.random()*9+1 防止永远 0 开始
          */
        double random = (Math.random()*9+1)*(Math.pow(10,size-1));
        int num = (int) random;
        System.out.println("num = " + num);


        NumberCodeResponse numberCodeResponse = new NumberCodeResponse();
        numberCodeResponse.setNumberCode(num);
        System.out.println(numberCodeResponse);
        return ResponseResult.success(numberCodeResponse);

    }
}
