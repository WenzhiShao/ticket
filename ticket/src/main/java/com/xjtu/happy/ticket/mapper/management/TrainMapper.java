package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.Train;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

    @Delete("delete from ticket.train where trainId = #{trainId}")
    public int DeleteTrainById(int trainId);
}
