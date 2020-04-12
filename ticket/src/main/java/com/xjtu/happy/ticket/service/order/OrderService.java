package com.xjtu.happy.ticket.service.order;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.xjtu.happy.ticket.bean.Orders;
import com.xjtu.happy.ticket.bean.TicketSeat;
import com.xjtu.happy.ticket.mapper.order.*;


@Service
public class OrderService {
	
	@Autowired
    OrderMapper orderMapper;
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public TicketSeat assignSeatByLock(Orders order,int trainId,String seatType,Date time) {
    	TicketSeat ticketSeat=orderMapper.selectSeatByLock(trainId, seatType,time);
    	if(ticketSeat==null)return null;
    	orderMapper.saveOrder(order);
    	orderMapper.assignSeatByLock(ticketSeat.getSeatId());
    	return ticketSeat;
    	
    }
    
    
}
