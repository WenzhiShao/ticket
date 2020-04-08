package com.xjtu.happy.ticket.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Station {
    //站点id
    private int stationId;

    //站点名称
    private String stationName;

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
