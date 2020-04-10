package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.Train;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface TrainMapper {
    @Insert("insert into train values(#{train.trainId},#{train.trainNum},#{train.trainTypeId}," +
            "#{train.startTime},#{train.endTime},#{train.startStationid},#{train.startStationName}," +
            "#{train.endStationid},#{train.endStationName})")
    public int InsertTrain(Train train);

    @Select("select * from train where trainId = #{trainId}")
    public Train FindTrainById(String trainId);

    @Select("select * from train")
    public List<Train> FindAllTrains();

    @Select("select count(*) from train")
    public int CountOfTrains();
}
