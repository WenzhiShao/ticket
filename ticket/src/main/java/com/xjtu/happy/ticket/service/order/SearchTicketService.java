package com.xjtu.happy.ticket.service.order;

import com.xjtu.happy.ticket.bean.TicketLeft;
import com.xjtu.happy.ticket.mapper.order.SearchTicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class SearchTicketService {
    @Autowired
    SearchTicketMapper queryTicket;
    public List<TicketLeft> queryTickets(String start, String end, Date date){
        return queryTicket.queryTickets(start,end,date);
    }
    public TicketLeft odTickets(int id,Date date){
        return queryTicket.odTicket(id,date);
    }
}
