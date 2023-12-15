package com.mashibing.contrlooer;

import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import com.mashibing.interinalcommon.responese.VerificationCodeDTO;
import com.mashibing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class servicepassagengeruser {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseResult loginOrRegister(@RequestBody VerificationCodeDTO verificationCodeDTO){

        ResponseResult ResponseResult = userService.loginOrReg(verificationCodeDTO.getPassengerPhone());
        return ResponseResult;
    }
}
