package com.mashibing.service;

import com.mashibing.dto.PassengerUser;
import com.mashibing.interinalcommon.constant.dto.ResponseResult;
import com.mashibing.interinalcommon.responese.VerificationCodeDTO;
import com.mashibing.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private PassengerMapper passengerMapper;

    public ResponseResult loginOrReg(String passengerPhone){

    // 根据手机号去查询用户信息
        HashMap<String, Object> map = new HashMap<>();
        map.put("passenger_phone",passengerPhone);
        List<PassengerUser> passengerUsers = passengerMapper.selectByMap(map);
        System.out.println( passengerUsers.size()==0 ?"无记录": passengerUsers.get(0).getPassengerName() );
        // 插入用户
        PassengerUser passengerUser = new PassengerUser();
        passengerUser.setPassengerName("李四");
        passengerUser.setPassengerPhone("15321513412");
        passengerUser.setGmtCreate(LocalDateTime.now());
        passengerUser.setGmtModified(LocalDateTime.now());
        passengerUser.setPassengerGender((byte) 0);
        passengerUser.setState((byte) 0);
        passengerMapper.insert(passengerUser);
        return ResponseResult.success("获取成功手机号"+passengerPhone);
    }
}
