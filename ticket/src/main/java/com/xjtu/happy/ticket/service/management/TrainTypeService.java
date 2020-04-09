package com.xjtu.happy.ticket.service;

import com.xjtu.happy.ticket.bean.TrainType;
import com.xjtu.happy.ticket.mapper.TrainTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainTypeService {
    @Autowired
    TrainTypeMapper trainTypeMapper;

    public TrainType FindTrainTypeById(int trainTypeid){
        return trainTypeMapper.FindTrainTypeById(trainTypeid);
    }

    public List<TrainType> FindAllTrainTypes(){
        return trainTypeMapper.FindAllTrainTypes();
    }
}
