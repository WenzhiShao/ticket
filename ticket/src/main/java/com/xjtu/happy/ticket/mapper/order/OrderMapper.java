package com.xjtu.happy.ticket.mapper.order;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xjtu.happy.ticket.bean.Orders;
import com.xjtu.happy.ticket.bean.TicketSeat;

@Mapper
public interface OrderMapper {
	
	
	//插入订单
	@Insert("INSERT INTO orders(orderNo,orderTime,trainId,ticektNum,totalPrice,orderStatus) VALUES(#{orderNo},#{orderTime},#{trainId},#{ticektNum},#{totalPrice},#{orderStatus})")
	public void saveOrder(Orders order);
	
	//找到分配座位的id
	@Select("SELECT * FROM ticketseat WHERE trainId=#{trainId} AND seatType=#{seatType} AND Date(travelTime)='2020-04-11' AND ticketSeatStatus='NORMAL' LIMIT 1 FOR UPDATE")
	public TicketSeat selectSeatByLock(@Param("trainId") int trainId,@Param("seatType") String seatType);
	
	//根据id更新座位状态
	@Update("UPDATE ticketseat SET ticketSeatStatus='SELL' WHERE seatId=#{seatId}")
	public void assignSeatByLock(@Param("seatId") int seatId);
	
	
}
