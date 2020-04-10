package com.xjtu.happy.ticket.mapper;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
//    @Select("select * from user where id = #{id}")
//    public User FindUserByID(String id);
    @Select("select password from user where username = #{username}")
    public String FindPasswordByUsername(String username);
}
