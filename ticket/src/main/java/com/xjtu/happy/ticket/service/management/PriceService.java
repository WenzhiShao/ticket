package com.xjtu.happy.ticket.service;

import com.xjtu.happy.ticket.bean.Price;
import com.xjtu.happy.ticket.mapper.PriceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceService {
    @Autowired
    PriceMapper priceMapper;

    public Price FindPriceByStationid(int startStation_id, int endStation_id){
        return priceMapper.FindPriceByStationid(startStation_id, endStation_id);
    }

    public boolean InsertPrice(Price price){
        return priceMapper.InsertPrice(price);
    }
}
