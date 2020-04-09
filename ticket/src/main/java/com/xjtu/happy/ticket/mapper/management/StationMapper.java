package com.xjtu.happy.ticket.mapper;

import com.xjtu.happy.ticket.bean.Station;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StationMapper {
    @Select("select * from station where stationId = #{stationid}")
    public Station FindStationById(int stationid);
    @Select("select * from station")
    public List<Station> FindAllStations();
}
