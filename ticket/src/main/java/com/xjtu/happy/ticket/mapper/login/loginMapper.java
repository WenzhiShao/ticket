package com.xjtu.happy.ticket.mapper.login;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface loginMapper {
    @Select("select * from user where userName = #{userName} and password = #{password} ")
    User Identity(String userName, String password);
    @Select("select * from user where userName = #{userName} and type = 'admin'" )
    User Check(String userName);
    @Select("select * from user where userName = #{userName}")
    User FindByname(String name);
}
