package com.xjtu.happy.ticket.controller.order;

import com.xjtu.happy.ticket.bean.SearchOrders;
import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.service.order.SearchOrdersServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/*查询订单
* */
@Controller
public class SearchOrdersController {
    @Autowired
    SearchOrdersServe Sorder;
    @RequestMapping("/orders")
    public String searchorders(HttpServletRequest request, Model model){
        HttpSession session=request.getSession();
        String username= (String) session.getAttribute("loginUser");
        List<SearchOrders> serchorder = Sorder.serchorder(username);
        model.addAttribute("orderlist",serchorder);
        return "orders";
    }
    @RequestMapping("/searchOrderTicket")
    @ResponseBody
    public Ticket SorderTicket(String orderno){
        return Sorder.orderTicket(orderno);
    }
}
