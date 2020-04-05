package com.xjtu.happy.ticket.mapper;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface UserMapper {
    @Select("select * from user where userName = #{userName} and password = #{password} ")
    User Identity(String userName, String password);
    @Select("select * from user where userName = #{userName} and type = '2'" )
    User Check(String userName);
}
