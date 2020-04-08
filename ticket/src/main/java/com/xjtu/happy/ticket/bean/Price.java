package com.xjtu.happy.ticket.bean;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Price {
    //出发站id
    private int startStationid;

    //到达站id
    private int endStationid;

    //A座价格
    private BigDecimal APrice;

    //B座价格
    private BigDecimal BPrice;

    //C座价格
    private BigDecimal CPrice;

    //列车类型id
    private int trainTypeId;

    public int getStartStationid() {
        return startStationid;
    }

    public void setStartStationid(int startStationid) {
        this.startStationid = startStationid;
    }

    public int getEndStationid() {
        return endStationid;
    }

    public void setEndStationid(int endStationid) {
        this.endStationid = endStationid;
    }

    public BigDecimal getAPrice() {
        return APrice;
    }

    public void setAPrice(BigDecimal APrice) {
        this.APrice = APrice;
    }

    public BigDecimal getBPrice() {
        return BPrice;
    }

    public void setBPrice(BigDecimal BPrice) {
        this.BPrice = BPrice;
    }

    public BigDecimal getCPrice() {
        return CPrice;
    }

    public void setCPrice(BigDecimal CPrice) {
        this.CPrice = CPrice;
    }

    public int getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(int trainTypeId) {
        this.trainTypeId = trainTypeId;
    }
}
