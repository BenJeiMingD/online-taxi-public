package com.mashibing.apipassager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(String date){
        System.out.println("date = " + date);
        return "test controller api"+date;
    }

    @RequestMapping("/j1")
    @ResponseBody
    public String j1(String name,String pass){
        String token ="";
        if ("zhang".equals(name)&&"123".equals(pass)){

            token = "请求服务成功";
            System.out.println("token = " + token);
        }
        return token;
    }

}
