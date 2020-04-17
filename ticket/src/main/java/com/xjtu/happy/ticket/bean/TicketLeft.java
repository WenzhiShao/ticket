package com.xjtu.happy.ticket.bean;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketLeft {
    private int  trainId;

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    //车次号
    private String trainNum;
    

	//出发时间
    private Time startTime;
    //到达时间
    private Time endTime;
    //出发站名称
    private String startStationName;
    //到达站名称
    private String endStationName;
    //乘车日期
    private java.sql.Date travelTime;

    private int A;
    private int B;
    private int C;
    
    private BigDecimal APrice;
    private BigDecimal BPrice;
    private BigDecimal CPrice;
    private BigDecimal Price;

    public String setSpendTime() throws ParseException {
        long time = this.endTime.getTime() - this.startTime.getTime();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String end = endTime.toString();
        String start = startTime.toString();
        if (time > 0) {
            Date enddate = df.parse("2020-4-5" + " " + end);
            Date startdate = df.parse("2020-4-5" + " " + start);
            long l = enddate.getTime() - startdate.getTime();
            long day = l / (24 * 60 * 60 * 1000);

            long hour = (l / (60 * 60 * 1000) - day * 24);

            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            return hour + "小时" + min + "分钟";


        } else {
            Date enddate = df.parse("2020-4-6" + " " + end);
            Date startdate = df.parse("2020-4-5" + " " + start);
            long l = enddate.getTime() - startdate.getTime();
            long day = l / (24 * 60 * 60 * 1000);

            long hour = (l / (60 * 60 * 1000) - day * 24);

            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            return hour + "小时" + min + "分钟";
        }
    }

    public String getTrainNum() {
        return trainNum;
    }

    public void setTrainNum(String trainNum) {
        this.trainNum = trainNum;
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

    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }
    

    public java.sql.Date getTravelTime() {
		return travelTime;
	}

	public void setTravelTime(java.sql.Date travelTime) {
		this.travelTime = travelTime;
	}

	public int getA() {
        return A;
    }


	public void setA(int a) {
        A = a;
    }

    public int getB() {
        return B;
    }

    public void setB(int b) {
        B = b;
    }

    public int getC() {
        return C;
    }

    public void setC(int c) {
        C = c;
    }

	public BigDecimal getAPrice() {
		return APrice;
	}

	public void setAPrice(BigDecimal aPrice) {
		APrice = aPrice;
	}

	public BigDecimal getBPrice() {
		return BPrice;
	}

	public void setBPrice(BigDecimal bPrice) {
		BPrice = bPrice;
	}

	public BigDecimal getCPrice() {
		return CPrice;
	}

	public void setCPrice(BigDecimal cPrice) {
		CPrice = cPrice;
	}

    public BigDecimal getPrice() {
        return Price;
    }

    public void setPrice(BigDecimal price) {
        Price = price;
    }
}
