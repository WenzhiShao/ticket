package com.xjtu.happy.ticket.mapper;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    
    //根据身份证号查询是否存在该乘客
    @Select("select * from user where identityNum=#{identityNum}")
    public User FindUserByIdentityNum(String identityNum);
}
