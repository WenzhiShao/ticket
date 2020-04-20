package com.xjtu.happy.ticket.service.management;

import com.xjtu.happy.ticket.mapper.management.UsersMapper;
import com.xjtu.happy.ticket.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersService {
    @Autowired
    UsersMapper usersMapper;

    public List<User> findAllUser(){
        return usersMapper.findAllUser();
    }

    public List<User> findAllAdmin(){return usersMapper.findAllAdmin();}

    public boolean deleteAdmin(int userId){
        if(usersMapper.deleteAdmin(userId)>0)
            return true;
        else
            return false;
    }

    public User findUser(int userId){return usersMapper.findUser(userId);}

    public boolean updateUserStatus(int userId, boolean activated){
        if(usersMapper.updateUserStatus(userId,!activated)>0)
            return true;
        else
            return false;
    }

}
