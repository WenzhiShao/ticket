package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.BlackList;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlackListMapper {

    @Insert("insert into blacklist values(#{id},#{userName},#{password}," +
            "#{name},#{identityNum})")
    int insertUser(BlackList blackList);

    @Select("select * from blacklist")
    List<BlackList> findAll();

    @Delete("delete from blacklist where id = #{id}")
    int delete(int id);
}
