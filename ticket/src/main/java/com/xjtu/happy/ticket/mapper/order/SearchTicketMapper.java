package com.xjtu.happy.ticket.mapper.order;
/*
*   查询车票的mapper
* */
import com.xjtu.happy.ticket.bean.TicketLeft;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Mapper
public interface SearchTicketMapper {
    @Select("select DISTINCT t.trainId,t.trainNum,t.startTime,t.startStationName,t.endTime,ticketseat.travelTime as travelTime,t.endStationName,COALESCE(sa.A,0) as A,\n" +
            "COALESCE(sb.B,0) as B,COALESCE(sc.C,0) as C from ticketseat,train t \n" +
            "  LEFT JOIN\n" +
            "   (Select trainId,count(seatId) as A  from ticketseat \n" +
            "    where seatType = 'A' and ticketSeatStatus = 'NORMAL' and travelTime = #{date} GROUP BY trainId) sa ON t.trainId = sa.trainId\n" +
            "  LEFT JOIN\n" +
            "   (Select trainId,count(seatId) as B from ticketseat \n" +
            "    where seatType = 'B' and ticketSeatStatus = 'NORMAL' and travelTime = #{date} GROUP BY trainId) sb ON t.trainId = sb.trainId\n" +
            "  LEFT JOIN\n" +
            "   (Select trainId,count(seatId) as C from ticketseat \n" +
            "    where seatType = 'C' and ticketSeatStatus = 'NORMAL' and travelTime = #{date} GROUP BY trainId) sc ON t.trainId = sc.trainId\n" +
            "where t.startStationName=#{start} and t.endStationName=#{end} AND t.trainId=ticketseat.trainId AND ticketseat.travelTime=#{date}")
    public List<TicketLeft> queryTickets(String start, String end, Date date);
    @Select("select t.trainId,t.trainNum,t.startTime,t.startStationName,t.endTime,t.endStationName,COALESCE(sa.ARemain,0) as ARemain,\n" +
            "COALESCE(sb.BRemain,0) as BRemain,COALESCE(sc.CRemain,0) as CRemain from train t \n" +
            "  LEFT JOIN\n" +
            "   (Select trainId,count(seatId) as ARemain from ticketseat \n" +
            "    where seatType = 'A' and ticketSeatStatus = 'NORMAL' and travelTime = #{date} GROUP BY trainId) sa ON t.trainId = sa.trainId\n" +
            "  LEFT JOIN\n" +
            "   (Select trainId,count(seatId) as BRemain from ticketseat \n" +
            "    where seatType = 'B' and ticketSeatStatus = 'NORMAL' and travelTime = #{date} GROUP BY trainId) sb ON t.trainId = sb.trainId\n" +
            "  LEFT JOIN\n" +
            "   (Select trainId,count(seatId) as CRemain from ticketseat \n" +
            "    where seatType = 'C' and ticketSeatStatus = 'NORMAL' and travelTime = #{date} GROUP BY trainId) sc ON t.trainId = sc.trainId\n" +
            "where t.trainId=id")
    public TicketLeft odTicket(int id, Date date);
}