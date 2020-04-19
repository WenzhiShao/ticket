package com.xjtu.happy.ticket.service.management;

import com.xjtu.happy.ticket.bean.BlackList;
import com.xjtu.happy.ticket.mapper.management.BlackListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlackListService {
    @Autowired
    BlackListMapper blackListMapper;
    public boolean insertUser(BlackList blackList){
        if(blackListMapper.insertUser(blackList)>0)
            return true;
        else
            return false;
    }


    public List<BlackList> findAll(){return blackListMapper.findAll();}

    public boolean delete(int id){
        if(blackListMapper.delete(id)>0)
            return true;
        else
            return false;
    }
}
