package com.xjtu.happy.ticket.service.order;


import java.util.Date;

import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.bean.TicketLeft;
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

	//锁定座位并插入订单
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public TicketSeat assignSeatByLock(Orders order,int trainId,String seatType,Date time) {
    	TicketSeat ticketSeat=orderMapper.selectSeatByLock(trainId, seatType,time);
    	if (ticketSeat == null)
    		return null;
    	orderMapper.saveOrder(order);
    	orderMapper.assignSeatByLock(ticketSeat.getSeatId());

    	return ticketSeat;
    }

    //插入票并修改订单状态
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public boolean InsertTicketAndChangeOrder(Ticket ticket)
	{
		if (orderMapper.InsertTicket(ticket) <= 0)
			return false;
		if(orderMapper.UpdateOrderStatus(ticket.getOrderNo()) <= 0)
			return false;
		return true;
	}

	//根据订单号获取原票信息
	public TicketLeft getOldTicketByOrderNo(String orderNo)
	{
		return orderMapper.getOldTicketByOrderNo(orderNo);
	}

	//改签操作
	@Transactional(isolation=Isolation.READ_COMMITTED)
	public boolean rebookTicket(String orderNo,Ticket ticket,int trainId,String seatType,Date time) {
		TicketSeat newticketSeat=orderMapper.selectSeatByLock(trainId, seatType,time);
		if (newticketSeat == null)
			return false;
		ticket.setSeatId(newticketSeat.getSeatId());
		ticket.setSeatNo(newticketSeat.getSeatType()+newticketSeat.getSeatNo());

		if(orderMapper.returnOldTicket(orderNo) < 1){
			return false;
		}
		if(orderMapper.returnOldSeat(orderNo) < 1)
		{
			return false;
		}
		orderMapper.assignSeatByLock(ticket.getSeatId());
		if(orderMapper.InsertTicket(ticket) < 1)
		{
			return false;
		}
		return true;
	}
}
