package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.TrainType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TrainTypeMapper {

    @Select("select * from traintype where trainTypeId = #{trainTypeid}")
    public TrainType FindTrainTypeById(int trainTypeid);

    @Select("select * from traintype")
    public List<TrainType> FindAllTrainTypes();

}
