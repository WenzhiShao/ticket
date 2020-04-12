package com.xjtu.happy.ticket.service.order;


import java.util.Date;

import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.bean.Train;
import com.xjtu.happy.ticket.mapper.management.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import com.xjtu.happy.ticket.bean.Orders;
import com.xjtu.happy.ticket.bean.TicketSeat;
import com.xjtu.happy.ticket.mapper.order.*;

import javax.swing.*;


@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;

	@Autowired
	TrainMapper trainMapper;
    
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public TicketSeat assignSeatByLock(Orders order,int trainId,String seatType,Date time) {
    	TicketSeat ticketSeat=orderMapper.selectSeatByLock(trainId, seatType,time);
    	if (ticketSeat == null)
    		return null;
    	orderMapper.saveOrder(order);
    	orderMapper.assignSeatByLock(ticketSeat.getSeatId());

    	return ticketSeat;
    }

	@Transactional(isolation=Isolation.READ_COMMITTED)
	public boolean InsertTicketAndChangeOrder(Ticket ticket)
	{
		if (orderMapper.InsertTicket(ticket) <= 0)
			return false;
		if(orderMapper.UpdateOrderStatus(ticket.getOrderNo()) <= 0)
			return false;
		return true;
	}
}
