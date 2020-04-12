package com.xjtu.happy.ticket.controller.order;


import com.xjtu.happy.ticket.bean.Station;
import com.xjtu.happy.ticket.bean.TicketLeft;
import com.xjtu.happy.ticket.service.login.LoginService;
import com.xjtu.happy.ticket.service.management.StationService;
import com.xjtu.happy.ticket.service.order.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SearchController {
    @Autowired
    SearchTicketService query;

    @Autowired
    LoginService userService;

    @Autowired
    StationService stationService;

    @RequestMapping("/index/list")
    public String querytickets(HttpServletRequest request, Model model, int startStationid , int endStationid,String date) throws ParseException {
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
        Date da= format.parse(date);         //字符串转化成Date类型（这里是java.util.Date）
        java.sql.Date d=new java.sql.Date(da.getTime());  //把java.util.Date转换成java.sql.Date
        List<TicketLeft> ticketLefts = query.queryTickets(startStationid, endStationid,d);
        if (ticketLefts.isEmpty()){
            HttpSession session = request.getSession();
            session.setAttribute("msgOfNullList","暂无列车信息");
//            model.addAttribute("msg", "暂无列车信息");
            return "redirect:/search";
        }else {
            System.out.println(ticketLefts); 
        model.addAttribute("tickets",ticketLefts);
        return "ticket";
    }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session=request.getSession();
        session.removeAttribute("loginUser");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userName")){
                Cookie usernamecookie = new Cookie("userName","");
                usernamecookie.setMaxAge(0);
                response.addCookie(usernamecookie);
                }
            }
        }
        return "redirect:/login";
    }

    //进入预订页面
    @RequestMapping(value ="/o",method = RequestMethod.GET)
    public String turnToOrder(HttpServletRequest req, @RequestParam Integer trainId,@RequestParam java.sql.Date time){
    	HttpSession session=req.getSession();

    	TicketLeft ticketSelected = query.odTickets(trainId,time);
    	ticketSelected.setTravelTime(time);
        session.setAttribute("ticketSelected", ticketSelected);
    
        return "order";

    }
 
    //进入查票页面
    @RequestMapping("/search")
    public String search(Model model, HttpServletRequest request, Map<String,Object> map){
        List<Station> stations = stationService.FindAllStations();
        model.addAttribute("stations", stations);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("userName")) {
                    String name = c.getValue();
                    Boolean filename = userService.FindByname(name);
                    if (filename) {
                        map.put("msg",c.getValue());
                        return "search";
                    }
                }
            }
        }
        return "search";
    }

}
