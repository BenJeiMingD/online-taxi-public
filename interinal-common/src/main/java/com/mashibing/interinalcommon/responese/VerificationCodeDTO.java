package com.mashibing.interinalcommon.responese;

import lombok.Data;

@Data
public class VerificationCodeDTO {

    private String passengerPhone;

    private String verificationCode;
}
