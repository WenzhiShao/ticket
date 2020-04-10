package com.xjtu.happy.ticket.controller.management;

import com.xjtu.happy.ticket.bean.Price;
import com.xjtu.happy.ticket.bean.Station;
import com.xjtu.happy.ticket.bean.Train;
import com.xjtu.happy.ticket.bean.TrainType;
import com.xjtu.happy.ticket.service.management.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

@Controller
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
        return "admin";
    }

    @GetMapping("/train")
    public String toAddTrain(Model model){
        //跳转到添加车次页面
        List<TrainType> trainTypes = trainTypeService.FindAllTrainTypes();
        List<Station> stations = stationService.FindAllStations();
        model.addAttribute("trainTypes", trainTypes);
        model.addAttribute("stations", stations);
        return "addtrain";
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


        //插入车次
        char trainTypeName;
        if (trainTypeId == 1)
            trainTypeName = 'G';
        else if (trainTypeId == 2)
            trainTypeName = 'D';
        else if (trainTypeId == 3)
            trainTypeName = 'T';
        else
            trainTypeName = 'K';
        train.setTrainId(trainService.CountOfTrains()+1);
        int trainId = train.getTrainId();
        train.setTrainNum("" + trainTypeName + startStationId + endStationId+trainId);
        train.setStartStationName(""+stationService.FindStationById(startStationId).getStationName());
        train.setEndStationName(""+stationService.FindStationById(endStationId).getStationName());
//        System.out.println("列车信息"+train);
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
            System.out.println("价格信息:"+price);
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
        String date = df.format(calendar.getTime());
        System.out.println("时间信息1："+df.format(calendar.getTime()));
        //因为第一条前没有‘,’，先放进去
        int count = 1;
        values = values + "('A'," + count + ",'" + trainId + "','" + date + "','NOMAL')";
        count++;
        //添加7天的座位数据
        for(int orderDays = 0; orderDays < 7; orderDays++){
            //按座位类型依次拼接串
            while (count <= ANum) {
                values = values + ",('A'," + count + ",'"+ trainId +"','"+ date +"','NOMAL')";
                count++;
            }
            count = 1;
            while (count <= BNum) {
                values = values + ",('B'," + count +",'"+ trainId +"','"+ date +"','NOMAL')";
                count++;
            }
            count = 1;
            while (count <= CNum) {
                values = values + ",('C'," + count +",'"+ trainId +"','"+ date +"','NOMAL')";
                count++;
            }
            count = 1;
            //每生成完一天的座位日期加一天
            calendar.add(Calendar.DATE, 1);
            date = df.format(calendar.getTime());
        }
        System.out.println(values);
//        success = ticketSeatService.InsertTicketSeat(values);
//        if(!success) {
//            model.addAttribute("seatmsg", "座位添加失败");
//            return "redirect:/admin";
//        }
//        else
//            model.addAttribute("seatmsg","添加成功");

        return "redirect:/trains";
    }



}
