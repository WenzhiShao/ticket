package com.xjtu.happy.ticket.service.login;

import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.mapper.login.loginMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class LoginService {
    @Resource
    loginMapper loginMapper;
    public boolean Identity(String userName, String password){
        if(loginMapper.Identity(userName, password)!=null){
         return true;
        }
        else {
            return false;
        }
    }
    public boolean Check(String userName){
        if(loginMapper.Check(userName)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public  boolean FindByname(String name) {
        if(loginMapper.FindByname(name)!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public User GetUserByname(String name) {
        return loginMapper.FindByname(name);
    }
    public boolean updateName(String userName,String name){
        if(loginMapper.updateName(userName,name)){
            return true;
        }
        return false;
    }
    public boolean updatePassword(String userName,String password){
        if(loginMapper.updatePassword(userName,password)){
            return true;
        }
        return false;
    }
    public boolean updateIdentityNum(String userName,String identityNum){
        if(loginMapper.updateIdentityNum(userName,identityNum)){
            return true;
        }
        return false;
    }
    public boolean updatePhone(String userName,String phone){
        if(loginMapper.updatePhone(userName,phone)){
            return true;
        }
        return false;
    }
    public boolean updateEmail(String userName,String email){
        if(loginMapper.updateEmail(userName,email)){
            return true;
        }
        return false;
    }

    public boolean activated(String userName){
        if(loginMapper.Activated(userName)==1){
            return true;
        }
        return false;
    }
    public int updateUser(String userName,String name,String identityNum,String phone,String email){
        return loginMapper.updateUser(userName,name,identityNum,phone,email);
    }
}
