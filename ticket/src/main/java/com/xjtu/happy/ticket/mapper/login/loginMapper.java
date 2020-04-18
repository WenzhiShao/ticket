package com.xjtu.happy.ticket.mapper.login;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface loginMapper {
    @Select("select * from user where userName = #{userName} and password = #{password} ")
    User Identity(String userName, String password);
    @Select("select * from user where userName = #{userName} and type = 'admin'" )
    User Check(String userName);
    @Select("select * from user where userName = #{userName}")
    User FindByname(String name);
    @Update("update user set name = #{name} where userName=#{userName}")
    Boolean updateName(String userName,String name);
    @Update("update user set password = #{password} where userName=#{userName}")
    Boolean updatePassword(String userName,String password);
    @Update("update user  set identityNum = #{identityNum} where userName=#{userName}")
    Boolean updateIdentityNum(String userName,String identityNum);
    @Update("update user  set phone = #{phone} where userName=#{userName}")
    Boolean updatePhone(String userName,String phone);
    @Update("update user  set email = #{email} where userName=#{userName}")
    Boolean updateEmail(String userName,String email);

}
