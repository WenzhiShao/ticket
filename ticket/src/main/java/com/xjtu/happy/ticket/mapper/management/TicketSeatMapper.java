package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.TicketSeat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TicketSeatMapper {
    @Select("select * from ticketseat where seatId = #{seatid}")
    public TicketSeat FindTicketSeatById(int seatid);

    @Insert("insert into ticketseat(seatType, seatNo, trainId, travelTime, ticketSeatStatus) values ${ticketSeats}")
    public int InsertTicketSeat(String ticketSeats);

    @Select("select count(*) from ticketseat")
    public int CountOfSeat();
}
