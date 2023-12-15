package com.mashibing.interinalcommon.constant;

import lombok.Getter;

import java.io.File;


public enum CommonStatusEnum {
    /**
     * 成功
     */
    SUCCESS(1,"success"),
    /**
     * 验证码错误
     */
    VERIFICTION_CODE_ERROR(1099,"验证码不正确"),
    /**
     * 失败
     */
    FAIL(0,"fail")
    ;
    @Getter
    private int code;
    @Getter
    private String value;

    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
