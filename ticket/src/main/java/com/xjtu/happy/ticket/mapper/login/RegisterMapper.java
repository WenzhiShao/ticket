package com.xjtu.happy.ticket.mapper.login;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface RegisterMapper {
    /*
            注册功能插入数据
     */
    @Insert("insert into user (userName,password,email,phone,name,identityNum,type) value(#{userName},#{password},#{email},#{phone},#{name},#{identityNum},#{type}) ")
    public int save(User user);
    /*
            注册功能查询用户名
     */
    @Select("select * from user where username = #{username} ")
    public User check( String username);


}
