package com.xjtu.happy.ticket.service;

import com.xjtu.happy.ticket.bean.Station;
import com.xjtu.happy.ticket.mapper.StationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    StationMapper stationMapper;

    public Station FindStationById(int stationid){
        return stationMapper.FindStationById(stationid);
    }
    public List<Station> FindAllStations() { return stationMapper.FindAllStations(); }
}
