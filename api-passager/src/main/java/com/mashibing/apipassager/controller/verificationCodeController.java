package com.mashibing.apipassager.controller;

import com.mashibing.interinalcommon.responese.VerificationCodeDTO;
import com.mashibing.apipassager.service.VerificationCodeService;
import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class verificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public ResponseResult verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        //传入实体类，用实体类get字段
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("passengerPhone = " + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }

    @PostMapping("/verification-code-check")
    public ResponseResult checkVerificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO){
        ResponseResult responseResult = verificationCodeService.checkCode(verificationCodeDTO.getPassengerPhone(), verificationCodeDTO.getVerificationCode());
        return ResponseResult.success(responseResult);
    }
}
