package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersMapper {
    @Select("select * from user where type='user'")
    List<User> findAllUser();

    @Select("select * from user where type='admin'")
    List<User> findAllAdmin();

    @Delete("delete from user where userId = #{userId}")
    int deleteAdmin(int userId);

    @Select("select * from user where userId = #{userId}")
    User findUser(int userId);


}
