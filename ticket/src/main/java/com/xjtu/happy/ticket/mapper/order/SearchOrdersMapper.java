package com.xjtu.happy.ticket.mapper.order;

import com.xjtu.happy.ticket.bean.SearchOrders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper

/*查询订单
* */
public interface SearchOrdersMapper {
    @Select("SELECT orderId,orderNo,trainNum,orderTime,ticektNum,orderStatus,totalPrice\n" +
            "FROM orders,`user`,train\n" +
            "WHERE orders.orderUserId=`user`.userId and userName='zy' and orders.trainId=train.trainId\n" +
            "ORDER BY orderTime DESC")
    public List<SearchOrders> searchorder(String username);

}
