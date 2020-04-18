package com.xjtu.happy.ticket.bean;

import lombok.Data;

import java.sql.Date;
@Data
public class SearchOrders {
    private  int orderId;
    private String orderNo;
    private Date orderTime;
    private int ticektNum;
    private int totalPrice;
    private String orderStatus;
    private String trainNum;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getTicektNum() {
        return ticektNum;
    }

    public void setTicektNum(int ticektNum) {
        this.ticektNum = ticektNum;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
    }
}
