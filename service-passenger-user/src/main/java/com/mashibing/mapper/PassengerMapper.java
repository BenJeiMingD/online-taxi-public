package com.mashibing.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mashibing.dto.PassengerUser;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
@Repository
public interface PassengerMapper extends BaseMapper<PassengerUser> {
}
