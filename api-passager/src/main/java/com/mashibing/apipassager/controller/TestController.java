package com.mashibing.apipassager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test")
    public String test(String date){
        System.out.println("date = " + date);
        return "test controller api"+date;
    }
}
