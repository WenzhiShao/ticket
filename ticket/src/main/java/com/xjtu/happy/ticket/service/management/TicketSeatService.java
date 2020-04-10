package com.xjtu.happy.ticket.service;

import com.xjtu.happy.ticket.bean.TicketSeat;
import com.xjtu.happy.ticket.mapper.TicketSeatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketSeatService {
    @Autowired
    TicketSeatMapper ticketSeatMapper;
    public TicketSeat FindTicketSeatById(int seatid){
        return ticketSeatMapper.FindTicketSeatById(seatid);
    }
    public boolean InsertTicketSeat(String ticketSeats){
        if (ticketSeatMapper.InsertTicketSeat(ticketSeats) >= 1)
            return true;
        else
            return false;
    }
    public int CountOfSeat(){
        return ticketSeatMapper.CountOfSeat();
    }
}
