package com.xjtu.happy.ticket.controller.order;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xjtu.happy.ticket.service.login.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import com.xjtu.happy.ticket.bean.Orders;
import com.xjtu.happy.ticket.bean.Ticket;
import com.xjtu.happy.ticket.bean.TicketLeft;
import com.xjtu.happy.ticket.bean.TicketSeat;
import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.service.order.*;
import com.xjtu.happy.ticket.service.login.*;

@Controller
public class OrderController {
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;

  //提交订单，跳转到支付页面
	@PostMapping("/submitOrder")
	public String submitOrder(User user,Model model,HttpServletRequest req,
			@RequestParam("ticketType") String ticketType,@RequestParam("seatType") String seatType,
			@RequestParam("name") String name,@RequestParam("identityType") String identityType,
			@RequestParam("identityNum") String identityNum,@RequestParam("phone") String phone) {
		HttpSession session=req.getSession();
		User userInDB=userService.FindUserByIdentityNum(user.getIdentityNum());
		
		if(userInDB==null || !user.getName().equals(userInDB.getName())) {
			model.addAttribute("msg","查无此人");
			return "order";
		}
		TicketLeft ticketSelected=(TicketLeft)session.getAttribute("ticketSelected");
		Orders order=new Orders();
		order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setOrderStatus("未支付");
		Date date=new Date();
		date.setTime(date.getTime()+8*60*60*1000);
		order.setOrderTime(date);
		order.setTicektNum(1);
		order.setTotalPrice(seatType=="A"?ticketSelected.getAPrice():(seatType=="B"?ticketSelected.getBPrice():ticketSelected.getCPrice()));
		order.setTrainId(ticketSelected.getTrainId());
		
		TicketSeat ticketSeat=orderService.assignSeatByLock(order,ticketSelected.getTrainId(),seatType,ticketSelected.getTravelTime());
		if(ticketSeat==null) {
			model.addAttribute("msg", seatType+"座票已售空");
			return "order";
		}
		
		model.addAttribute("ticketType", ticketType);
		model.addAttribute("seatTypeNo", seatType+ticketSeat.getSeatNo());
		model.addAttribute("name", name);
		model.addAttribute("identityType", identityType);
		model.addAttribute("identityNum", identityNum);
		model.addAttribute("phone", phone);
		return "pay";
		
		
	}
	



}
