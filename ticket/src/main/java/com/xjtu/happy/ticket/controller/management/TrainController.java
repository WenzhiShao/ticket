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
        //插入车次
        train.setTrainId(trainService.CountOfTrains());
        train.setTrainNum(""+train.getTrainTypeId()+train.getStartStationid()+train.getEndStationid()+train.getTrainId());
        train.setStartStationName(""+stationService.FindStationById(train.getStartStationid()));
        train.setEndStationName(""+stationService.FindStationById(train.getEndStationid()));
        success = trainService.InsertTrain(train);
        if(!success)
            model.addAttribute("trainmsg","车次添加失败");

        //插入价格
        /*============================须加入判断是否存在的条件=============================*/
        Price price = new Price();
        price.setAPrice(APrice);
        price.setBPrice(BPrice);
        price.setCPrice(CPrice);
        price.setStartStationid(train.getStartStationid());
        price.setEndStationid(train.getEndStationid());
        price.setTrainTypeId(train.getTrainTypeId());
        success = priceService.InsertPrice(price);
        if(!success)
            model.addAttribute("pricemsg","价格表插入失败");

        //生成座位
        TrainType trainType = trainTypeService.FindTrainTypeById(train.getTrainTypeId());
        int ANum = trainType.getANum(),
                BNum = trainType.getBNum(),
                CNum = trainType.getCNum();
        String  values = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        String trainId = "" + train.getTrainId();
        values += "('A'," + "" + ANum + ",'" + trainId + "','" + calendar + "','NOMAL')";
        ANum--;
        for(int orderDays = 0; orderDays < 7; orderDays++){
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
            calendar.add(Calendar.DATE, 1);
        }
        ticketSeatService.InsertTicketSeat(values);
        if(!success)
            model.addAttribute("price","价格表插入失败");

        return "redirect:/trains";
    }



}
