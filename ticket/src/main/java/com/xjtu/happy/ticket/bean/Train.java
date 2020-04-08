package com.xjtu.happy.ticket.bean;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Getter
@Setter
public class Train {
    //车次id
    private int trainId;

    //车次号
    private String trainNum;

    //列车类型id
    private int trainTypeId;

    //出发时间
    private Time startTime;

    //到达时间
    private Time endTime;

    //出发站id
    private int startStationid;

    //出发站名称
    private String startStationName;

    //到达站id
    private int endStationid;

    //到达站名称
    private String endStationName;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }

    public int getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(int trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public int getStartStationid() {
        return startStationid;
    }

    public void setStartStationid(int startStationid) {
        this.startStationid = startStationid;
    }

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public int getEndStationid() {
        return endStationid;
    }

    public void setEndStationid(int endStationid) {
        this.endStationid = endStationid;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }
}
