package com.mashibing.apipassager.controller;

import com.mashibing.apipassager.request.VerificationCodeDTO;
import com.mashibing.apipassager.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class verificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;

    @GetMapping("/verification-code")
    public String verificationCode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
        //传入实体类，用实体类get字段
        String passengerPhone = verificationCodeDTO.getPassengerPhone();
        System.out.println("passengerPhone = " + passengerPhone);
        return verificationCodeService.generatorCode(passengerPhone);
    }
}
