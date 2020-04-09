package com.xjtu.happy.ticket.controller.order;
/*
 查询车票
* */
import com.xjtu.happy.ticket.bean.TicketLeft;
import com.xjtu.happy.ticket.service.order.SearchTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    SearchTicketService query;
    @RequestMapping("/index/list")

    public String querytickets(Model model, String start , String end,String date) throws ParseException {
        SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd");
        Date da= format.parse(date);         //字符串转化成Date类型（这里是java.util.Date）
        java.sql.Date d=new java.sql.Date(da.getTime());  //把java.util.Date转换成java.sql.Date
        List<TicketLeft> ticketLefts = query.queryTickets(start, end,d);
        if (ticketLefts.isEmpty()){
            model.addAttribute("msg","暂无列车信息");
            return "index";
        }else {
            System.out.println(ticketLefts);
            model.addAttribute("tickets",ticketLefts);
            return "ticket";
        }
    }
    //转跳到订票页面
    @RequestMapping(value ="/o",method = RequestMethod.GET)
    public String turnToOrder(Model model, @RequestParam String trainNum,@RequestParam Time startTime,
                              @RequestParam String startst,@RequestParam Time endtime,@RequestParam String endst,
                              @RequestParam Integer A ,@RequestParam Integer B,@RequestParam Integer C,@RequestParam java.sql.Date time){

        model.addAttribute("num",trainNum);
        model.addAttribute("sT",startTime);
        model.addAttribute("sst",startst);
        model.addAttribute("eT",endtime);
        model.addAttribute("est",endst);
        model.addAttribute("A",A);
        model.addAttribute("B",B);
        model.addAttribute("C",C);
        model.addAttribute("time",time);
        return "order";

    }
    //进入查票页面
    @RequestMapping("/search")
    public String search(){
        return "search";
    }


}
