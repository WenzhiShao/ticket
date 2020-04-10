package com.xjtu.happy.ticket.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.xjtu.happy.ticket.bean.Orders;
import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.bean.TicketSeat;
import com.xjtu.happy.ticket.mapper.OrderMapper;


@Service
public class OrderService {
	
	@Autowired
    OrderMapper orderMapper;
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public Ticket assignSeatByLock(Orders order,int trainId,String seatType) {
    	
    	Ticket ticket=new Ticket();
    	TicketSeat ticketSeat=orderMapper.selectSeatByLock(trainId, seatType);
    	if(ticketSeat==null)return null;
    	orderMapper.saveOrder(order);
    	ticket.setSeatId(ticketSeat.getSeatId());
    	orderMapper.assignSeatByLock(ticketSeat.getSeatId());
    	return ticket;
    	
    }
    
    
}
