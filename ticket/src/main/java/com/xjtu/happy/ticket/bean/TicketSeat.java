package com.xjtu.happy.ticket.bean;

import java.util.Date;

public class TicketSeat {
    //座位id
    private int seatId;

    //座位类型
    private String seatType;

    //座位编号
    private String seatNo;

    //车次id
    private int trainId;

    //乘车日期
    private Date travelTime;

    //座位状态
    private String ticketSeatStatus;

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public Date getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(Date travelTime) {
        this.travelTime = travelTime;
    }

    public String getTicketSeatStatus() {
        return ticketSeatStatus;
    }

    public void setTicketSeatStatus(String ticketSeatStatus) {
        this.ticketSeatStatus = ticketSeatStatus;
    }

    public void setTicketSeat(String seatType,String seatNo,int trainId,Date travelTime,String ticketSeatStatus){
        this.seatType = seatType;
        this.seatNo = seatNo;
        this.trainId = trainId;
        this.travelTime = travelTime;
        this.ticketSeatStatus = ticketSeatStatus;
    }
}
