package com.xjtu.happy.ticket.service.login;

import com.xjtu.happy.ticket.mapper.login.findPassMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FindPassService {
    @Resource
    findPassMapper findPassMapper;
    public boolean checkUser(String username){
        if (findPassMapper.checkUser(username)!=null){
            return true;
        }else {
        return false;}
    }
    public String getEmail(String username){
            String nowEmail = findPassMapper.checkUser(username);
            return nowEmail;
    }
    public  boolean changepw(String userName,String password){
        if(findPassMapper.changePw(userName,password)){
            return true;
        }
        return false;
    }
}
