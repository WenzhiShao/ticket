package com.xjtu.happy.ticket.service.login;


import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.mapper.login.RegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RegisterService {
    @Resource
    @Autowired
    private RegisterMapper registerMapper;
    //用户名是否重复
    public boolean IsUserExit(String name){
        if(registerMapper.check(name) != null)
            return true;
        return false;
    }

    //插入用户
    public boolean save(User user){
        if(registerMapper.save(user) == 1)
            return true;
        return false;
    }
}
