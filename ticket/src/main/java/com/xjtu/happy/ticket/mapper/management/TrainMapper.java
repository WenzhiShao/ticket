package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.Train;
import org.apache.ibatis.annotations.*;

import java.sql.Time;
import java.util.List;

@Mapper
public interface TrainMapper {
    @Insert("insert into train values(#{trainId},#{trainNum},#{trainTypeId}," +
            "#{startTime},#{endTime},#{startStationid},#{startStationName}," +
            "#{endStationid},#{endStationName})")
    public int InsertTrain(Train train);

    @Select("select * from train where trainNum = #{trainNum}")
    public Train FindTrainByNum(String trainNum);

    @Select("select * from train where trainId = #{trainId}")
    public Train FindTrainById(int trainId);

    @Select("select * from train")
    public List<Train> FindAllTrains();

    @Select("select count(*) from train")
    public int CountOfTrains();

    @Delete("delete from train where trainId = #{trainId}")
    public int DeleteTrainById(int trainId);

    @Update("update train set startTime = #{startTime}, endTime = #{endTime} where trainId = #{trainId}")
    public int UpdateTrainById(int trainId, Time startTime, Time endTime);
}
