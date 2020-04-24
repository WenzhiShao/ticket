package com.xjtu.happy.ticket.controller.order;

import com.xjtu.happy.ticket.bean.SearchOrders;
import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.service.order.SearchOrdersServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

/*查询订单
* */
@Controller
public class SearchOrdersController {
    //查询订单页面
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
    //查询详情返回车票信息
    @RequestMapping(value = "/searchOrderTicket",method = RequestMethod.GET)
    public String SorderTicket(HttpServletRequest request,Model model,String orderno) throws ParseException {
        Ticket ticket = Sorder.orderTicket(orderno);
        model.addAttribute("ticket",ticket);
        return "updateTicket";
    }
    //转跳到支付界面
    @RequestMapping("/gotopay")
    public String gotopay(HttpServletRequest request,Model model,String orderno,HttpSession session){
        session.setAttribute("orderno",orderno);
        User user = Sorder.sUser(orderno);
        model.addAttribute("ticketType","成人票");
        model.addAttribute("name",user.getName());
        model.addAttribute("identityType","居民身份证");
        model.addAttribute("identityNum", user.getIdentityNum());
        model.addAttribute("phone", user.getPhone());
        return "pay";
    }
    //跳转到改签页面
    @RequestMapping("/gotoupdate")
    public String gotoupdate(HttpSession session,Model model, @RequestParam String orderNoR){
        session.setAttribute("orderNoR",orderNoR);
        return "redirect:/search";
    }
}
