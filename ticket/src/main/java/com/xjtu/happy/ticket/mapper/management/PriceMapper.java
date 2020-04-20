package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.Price;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PriceMapper {

    @Select("select * from price where startStationid = #{startStation_id} " +
            "and endStationid = #{endStation_id} and trainTypeId = #{trainTypeId}")
    public Price FindPriceByTrainAndStation(int startStation_id,int endStation_id,int trainTypeId);

    @Insert("insert into price values(#{startStationid},#{endStationid}," +
            "#{APrice},#{BPrice},#{CPrice},#{trainTypeId})")
    public int InsertPrice(Price price);

    @Select("select count(*) from price where startStationid = #{startStation_id} and endStationid = #{endStation_id} and trainTypeId = #{trainTypeId}")
    public int CheckExist(int startStation_id,int endStation_id,int trainTypeId);

    @Update("update price set APrice=#{APrice},BPrice=#{BPrice},CPrice=#{CPrice} where startStationid=#{startStationid} and endStationid=#{endStationid} and trainTypeId=#{trainTypeId};")
    public int UpdatePrice(Price price);
}
