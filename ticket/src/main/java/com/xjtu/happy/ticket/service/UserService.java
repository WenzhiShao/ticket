package com.xjtu.happy.ticket.service;

import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User FindUserByID(String id){
        return userMapper.FindUserByID(id);
    }
}
