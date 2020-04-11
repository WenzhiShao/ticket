package com.xjtu.happy.ticket.service.management;

import com.xjtu.happy.ticket.bean.Price;
import com.xjtu.happy.ticket.mapper.management.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    @Autowired
    PriceMapper priceMapper;

    public Price FindPriceByTrainAndStation(int startStation_id, int endStation_id, int trainTypeId){
        return priceMapper.FindPriceByTrainAndStation(startStation_id, endStation_id, trainTypeId);
    }

    public boolean InsertPrice(Price price){
        if (priceMapper.InsertPrice(price) >= 1)
            return true;
        else
            return false;
    }

    public boolean CheckExist(int startStation_id,int endStation_id,int trainTypeId){
        if (priceMapper.CheckExist(startStation_id,endStation_id,trainTypeId) >= 1)
            return true;
        else
            return false;
    }
}
