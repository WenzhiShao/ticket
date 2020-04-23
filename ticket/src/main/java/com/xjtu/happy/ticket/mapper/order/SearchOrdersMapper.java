package com.xjtu.happy.ticket.mapper.order;

import com.xjtu.happy.ticket.bean.SearchOrders;
import com.xjtu.happy.ticket.bean.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

/*查询订单
* */
public interface SearchOrdersMapper {
    @Select("SELECT orderId,orderNo,trainNum,orderTime,ticektNum,orderStatus,totalPrice\n" +
            "FROM orders,user,train\n" +
            "WHERE orders.orderUserId=user.userId and userName=#{username} and orders.trainId=train.trainId\n" +
            "ORDER BY orderTime DESC")
    public List<SearchOrders> searchorder(String username);
    //通过订单号查询订单下的票的详细信息
    @Select("SELECT * \n" +
            "FROM ticket\n" +
            "WHERE orderNo=#{orderno}\n" +
            "ORDER BY ticketId DESC LIMIT 1")
    public Ticket orderTicket(String orderno);

}
