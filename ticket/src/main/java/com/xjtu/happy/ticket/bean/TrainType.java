package com.xjtu.happy.ticket.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainType {
    //列车类型id
    private int trainTypeId;

    //A座数量
    private int ANum;

    //B座数量
    private int BNum;

    //C座数量
    private int CNum;

    public int getTrainTypeId() {
        return trainTypeId;
    }

    public void setTrainTypeId(int trainTypeId) {
        this.trainTypeId = trainTypeId;
    }

    public int getANum() {
        return ANum;
    }

    public void setANum(int ANum) {
        this.ANum = ANum;
    }

    public int getBNum() {
        return BNum;
    }

    public void setBNum(int BNum) {
        this.BNum = BNum;
    }

    public int getCNum() {
        return CNum;
    }

    public void setCNum(int CNum) {
        this.CNum = CNum;
    }
}
