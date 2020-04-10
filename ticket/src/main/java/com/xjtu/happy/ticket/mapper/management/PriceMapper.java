package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.Price;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PriceMapper {

    @Select("select * from price where startStationid = #{startStation_id} and endStationid = #{endStation_id}")
    public Price FindPriceByStationid(int startStation_id,int endStation_id);

    @Insert("insert into price values(#{price.startStationid},#{price.endStationid}," +
            "#{price.APrice},#{price.BPrice},#{price.CPrice},#{price.trainTypeId})")
    public int InsertPrice(Price price);

    @Select("select count(*) from price where startStationid = #{startStation_id} and endStationid = #{endStation_id} and trainTypeId = #{trainTypeId}")
    public int CheckExist(int startStation_id,int endStation_id,int trainTypeId);
}
