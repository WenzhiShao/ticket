package com.xjtu.happy.ticket.controller.order;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.xjtu.happy.ticket.bean.*;
import com.xjtu.happy.ticket.service.login.*;
import com.xjtu.happy.ticket.service.management.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;

import com.xjtu.happy.ticket.service.order.*;
import com.xjtu.happy.ticket.service.login.*;

@Controller
public class OrderController {
	@Autowired
	UserService userService;
	@Autowired
	OrderService orderService;
	@Autowired
	LoginService loginService;
	@Autowired
	TrainService trainService;

    //提交订单，跳转到支付页面
	@PostMapping("/submitOrder")
	public String submitOrder(User user,Model model,HttpServletRequest req,
			@RequestParam("ticketType") String ticketType,@RequestParam("seatType") String seatType,
			@RequestParam("name") String name,@RequestParam("identityType") String identityType,
			@RequestParam("identityNum") String identityNum,@RequestParam("phone") String phone) {
		HttpSession session=req.getSession();
		Cookie[] cookies = req.getCookies();
		String strName = "";
		for (Cookie cookie : cookies) {
			switch(cookie.getName()){
				case "userName":
					strName = cookie.getValue();
					break;
				default:
					break;
			}
		}
		User userInDB = userService.FindUserByIdentityNum(user.getIdentityNum());
		User operateUser = loginService.GetUserByname(strName);
		if(userInDB==null || !user.getName().equals(userInDB.getName())) {
			model.addAttribute("msg","查无此人");
			return "order";
		}
		TicketLeft ticketSelected=(TicketLeft)session.getAttribute("ticketSelected");
		Orders order=new Orders();
		order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
		order.setOrderStatus("unpaid");
		Date date=new Date();
		date.setTime(date.getTime()+8*60*60*1000);
		order.setOrderTime(date);
		order.setTicektNum(1);
		order.setTotalPrice(seatType=="A"?ticketSelected.getAPrice():(seatType=="B"?ticketSelected.getBPrice():ticketSelected.getCPrice()));
		order.setTrainId(ticketSelected.getTrainId());
		order.setOrderUserId(operateUser.getUserId());

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

		session.setAttribute("ticketSelected", ticketSelected);
		session.setAttribute("order", order);
		session.setAttribute("ticketseat",ticketSeat);
		session.setAttribute("name",name);
		session.setAttribute("identityNum",identityNum);
		return "pay";
		
		
	}

	//改签操作
	@RequestMapping("/submitRebook")
	public String submitRebook(Model model,HttpServletRequest req,
							   @RequestParam("ticketType") String ticketType,@RequestParam("seatType") String seatType,
							   @RequestParam("name") String name,@RequestParam("identityType") String identityType,
							   @RequestParam("identityNum") String identityNum,@RequestParam("phone") String phone)
	{
		HttpSession session=req.getSession();
		TicketLeft ticketSelected=(TicketLeft)session.getAttribute("ticketSelected");
		String orderNoR = (String)session.getAttribute("orderNoR");

		Cookie[] cookies = req.getCookies();
		String strName = "";
		for (Cookie cookie : cookies) {
			switch(cookie.getName()){
				case "userName":
					strName = cookie.getValue();
					break;
				default:
					break;
			}
		}
		User operateUser = loginService.GetUserByname(strName);
		Train train = trainService.FindTrainById(ticketSelected.getTrainId());

		Ticket newTicket = new Ticket();
		newTicket.setOrderNo(orderNoR);
		newTicket.setTrainNum(train.getTrainNum());
		newTicket.setTravelTime(ticketSelected.getTravelTime());
		newTicket.setPrice(seatType=="A"?ticketSelected.getAPrice():(seatType=="B"?ticketSelected.getBPrice():ticketSelected.getCPrice()));
		newTicket.setName(name);
		newTicket.setIdentityNum(identityNum);
		newTicket.setStartTime(train.getStartTime());
		newTicket.setEndTime(train.getEndTime());
		newTicket.setStartStationid(train.getStartStationid());
		newTicket.setEndStationid(train.getEndStationid());
		newTicket.setStartStationName(train.getStartStationName());
		newTicket.setEndStationName(train.getEndStationName());
		newTicket.setTicketStatus("rebooked");
		newTicket.setTicketUserId(operateUser.getUserId());
		if (orderService.rebookTicket(orderNoR,newTicket,ticketSelected.getTrainId(),seatType,ticketSelected.getTravelTime()))
			return "orders";
		return "rebook";
	}
}
