package com.mashibing.interinalcommon.constant.dto;

import com.mashibing.interinalcommon.constant.CommonStatusEnum;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseResult <T>{


    /**
     * @Accessors(chain = true)
     * 链式调用，set完返回整个对象
     */
    private int code;

    private String message;

    private T data;
    public static <T> ResponseResult success(){

        // ResponseResult <T> new 一个这个方法，传参 可变参数
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue());
    }

    public static <T> ResponseResult success(T data){

        // ResponseResult <T> new 一个这个方法，传参 可变参数
        return new ResponseResult().setCode(CommonStatusEnum.SUCCESS.getCode()).setMessage(CommonStatusEnum.SUCCESS.getValue()).setData(data);
    }
    /**
     * 返回错误码
     * @param code
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code, String message){

        // ResponseResult <T> new 一个这个方法，传参 可变参数
        return new ResponseResult().setCode(code).setMessage(message);
    }

    /**
     * 返回详细信息
     * @param code
     * @param message
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(int code, String message, String data){

        // ResponseResult <T> new 一个这个方法，传参 可变参数
        return new ResponseResult().setCode(code).setMessage(message).setData(data);
    }

    /**
     * 返回详细信息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult fail(T data){

        // ResponseResult <T> new 一个这个方法，传参 可变参数
        return new ResponseResult().setData(data);
    }

}
