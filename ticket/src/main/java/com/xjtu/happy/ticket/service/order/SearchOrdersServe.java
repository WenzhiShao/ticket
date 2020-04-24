package com.xjtu.happy.ticket.service.order;

import com.xjtu.happy.ticket.bean.SearchOrders;
import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.mapper.order.SearchOrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*查询订单
* */
@Service
public class SearchOrdersServe {
    @Autowired
    SearchOrdersMapper sercho;
    public List<SearchOrders> serchorder(String username){
        return sercho.searchorder(username);
    }
    public Ticket orderTicket(String orderno){
        return sercho.orderTicket(orderno);
    }
    public User sUser(String orderno){
        return sercho.searchUser(orderno);
    }
}
