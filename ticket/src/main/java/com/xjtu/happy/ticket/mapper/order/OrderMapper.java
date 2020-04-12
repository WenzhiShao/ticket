package com.xjtu.happy.ticket.mapper.order;

import java.util.Date;

import com.xjtu.happy.ticket.bean.Ticket;
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
	@Insert("INSERT INTO orders(orderNo,orderTime,trainId,ticektNum,totalPrice,orderStatus,orderUserId) VALUES(#{orderNo},#{orderTime},#{trainId},#{ticektNum},#{totalPrice},#{orderStatus},#{orderUserId})")
	public void saveOrder(Orders order);

	@Update("UPDATE orders SET orderStatus = 'paid' where orderNo = #{orderNo}")
	public int UpdateOrderStatus(String orderNo);
	
	//找到分配座位的id
	@Select("SELECT * FROM ticketseat WHERE trainId=#{trainId} AND seatType=#{seatType} AND Date(travelTime)=#{time} AND ticketSeatStatus='NORMAL' LIMIT 1 FOR UPDATE")
	public TicketSeat selectSeatByLock(@Param("trainId") int trainId,@Param("seatType") String seatType,@Param("time") Date time);
	
	//根据id更新座位状态
	@Update("UPDATE ticketseat SET ticketSeatStatus='SELL' WHERE seatId=#{seatId}")
	public void assignSeatByLock(@Param("seatId") int seatId);

	//插入座位
	@Insert("INSERT INTO ticket(orderNo,trainNum,seatId,seatNo,travelTime,price,name,identityNum,startTime,endTime,startStationid,startStationName,endStationid,endStationName,ticketStatus,ticketUserId) " +
			"VALUES(#{orderNo},#{trainNum},#{seatId},#{seatNo},#{travelTime},#{price},#{name},#{identityNum},#{startTime},#{endTime},#{startStationid},#{startStationName},#{endStationid},#{endStationName},#{ticketStatus},#{ticketUserId})")
	public int InsertTicket(Ticket ticket);
}
