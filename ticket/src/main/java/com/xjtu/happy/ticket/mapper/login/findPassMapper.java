package com.xjtu.happy.ticket.mapper.login;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface findPassMapper {
    @Select("select email from user where userName = #{userName}")
    String checkUser(String userName);
    @Update("update user set password = #{password} where userName = #{userName}" )
    Boolean changePw(String userName,String password);
}
