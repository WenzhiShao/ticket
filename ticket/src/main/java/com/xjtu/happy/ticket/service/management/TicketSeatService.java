package com.xjtu.happy.ticket.service.management;

import com.xjtu.happy.ticket.bean.TicketSeat;
import com.xjtu.happy.ticket.mapper.management.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketSeatService {
    @Autowired
    TicketSeatMapper ticketSeatMapper;
    public TicketSeat FindTicketSeatById(int seatid){
        return ticketSeatMapper.FindTicketSeatById(seatid);
    }
    public boolean InsertTicketSeat(List<TicketSeat> ticketSeats){
        if (ticketSeatMapper.InsertTicketSeats(ticketSeats) >= 1)
            return true;
        else
            return false;
    }
    public int CountOfSeat(){
        return ticketSeatMapper.CountOfSeat();
    }
}
