package com.mashibing.apipassager.remote;

import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import com.mashibing.interinalcommon.responese.NumberCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("service-verificationcode")
public interface ServiceVefifcationcodeClient {
    @RequestMapping(method = RequestMethod.GET,value = "/numberCode/{size}") // 这里会调用 serviceverificationcode controller的路径api，返回验证码
    ResponseResult <NumberCodeResponse> getNumberCode(@PathVariable("size") int size);
}
