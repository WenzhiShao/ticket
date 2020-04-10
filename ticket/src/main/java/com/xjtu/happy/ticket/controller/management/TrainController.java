package com.xjtu.happy.ticket.controller;

import com.xjtu.happy.ticket.bean.Price;
import com.xjtu.happy.ticket.bean.Station;
import com.xjtu.happy.ticket.bean.Train;
import com.xjtu.happy.ticket.bean.TrainType;
import com.xjtu.happy.ticket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TrainController {
    @Autowired
    TrainService trainService;

    @Autowired
    TrainTypeService trainTypeService;

    @Autowired
    StationService stationService;

    @Autowired
    PriceService priceService;

    @Autowired
    TicketSeatService ticketSeatService;

    @GetMapping("/trains")
    public String trainlist(Model model) {
        List<Train> trains = trainService.FindAllTrains();
        model.addAttribute("trains", trains);
        return "train/list";
    }

    @GetMapping("/train")
    public String toAddTrain(Model model){
        //跳转到添加车次页面
        List<TrainType> trainTypes = trainTypeService.FindAllTrainTypes();
        List<Station> stations = stationService.FindAllStations();
        model.addAttribute("trainTypes", trainTypes);
        model.addAttribute("stations", stations);
        return "train/add";
    }

    //添加车次
    @PostMapping("/train")
    public String addTrain(@RequestParam("APrice") BigDecimal APrice,
                           @RequestParam("BPrice") BigDecimal BPrice,
                           @RequestParam("CPrice") BigDecimal CPrice,
                           Train train, Model model){

        boolean success = false;
        //预存数据，减少冗余
        int trainTypeId = train.getTrainTypeId();
        int startStationId = train.getStartStationid();
        int endStationId = train.getEndStationid();
        int trainId = train.getTrainId();

        //插入车次
        train.setTrainId(trainService.CountOfTrains());
        train.setTrainNum(""+trainTypeId+startStationId+endStationId+trainId);
        train.setStartStationName(""+stationService.FindStationById(startStationId));
        train.setEndStationName(""+stationService.FindStationById(endStationId));
        success = trainService.InsertTrain(train);
        if(!success) {
            model.addAttribute("trainmsg", "车次添加失败");
            return "redirect:/trains";
        }

        //插入价格
        //判断是否存在该类型车次价格
        if (!priceService.CheckExist(startStationId,endStationId,trainTypeId)) {
            Price price = new Price();
            price.setAPrice(APrice);
            price.setBPrice(BPrice);
            price.setCPrice(CPrice);
            price.setStartStationid(startStationId);
            price.setEndStationid(endStationId);
            price.setTrainTypeId(trainTypeId);
            success = priceService.InsertPrice(price);
            if (!success) {
                model.addAttribute("pricemsg", "价格表插入失败");
                return "redirect:/trains";
            }
        }

        //生成座位
        TrainType trainType = trainTypeService.FindTrainTypeById(trainTypeId);
        int ANum = trainType.getANum(),
                BNum = trainType.getBNum(),
                CNum = trainType.getCNum();
        //待拼接的字符串
        String  values = "";
        //获取当前日期
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //一天后的日期
        calendar.add(Calendar.DATE, 1);
        //因为第一条前没有‘,’，先放进去
        values += "('A'," + "" + ANum + ",'" + trainId + "','" + calendar + "','NOMAL')";
        ANum--;
        //添加7天的座位数据
        for(int orderDays = 0; orderDays < 7; orderDays++){
            //按座位类型依次拼接串
            while (ANum > 0) {
                values = values + ",('A',"+ ""+ ANum +",'"+ trainId +"','"+ calendar +"','NOMAL')";
                ANum--;
            }
            while (BNum > 0) {
                values = values + ",('B',"+ ""+ BNum +",'"+ trainId +"','"+ calendar +"','NOMAL')";
                BNum--;
            }
            while (CNum > 0) {
                values = values + ",('C',"+ ""+ CNum +",'"+ trainId +"','"+ calendar +"','NOMAL')";
                CNum--;
            }
            //每生成完一天的座位日期加一天
            calendar.add(Calendar.DATE, 1);
        }
        success = ticketSeatService.InsertTicketSeat(values);
        if(!success) {
            model.addAttribute("seatmsg", "座位添加失败");
            return "redirect:/trains";
        }
        else
            model.addAttribute("seatmsg","添加成功");

        return "redirect:/trains";
    }



}
