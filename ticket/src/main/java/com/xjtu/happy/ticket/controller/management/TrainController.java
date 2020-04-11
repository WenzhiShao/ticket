package com.xjtu.happy.ticket.controller.management;

import com.xjtu.happy.ticket.bean.*;
import com.xjtu.happy.ticket.service.management.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

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
//            System.out.println("价格信息:"+price);
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
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        //一天后的日期
        calendar.add(Calendar.DATE, 1);
//        String date = df.format(calendar.getTime());
        //因为第一条前没有‘,’，先放进去
        int count = 1;
        List<TicketSeat> ticketSeats = new ArrayList<>();
        TicketSeat ticketSeat = new TicketSeat();
        ticketSeat.setTicketSeat("A","" + count, trainId, calendar.getTime(),"NORMAL");
        ticketSeats.add(ticketSeat);
        count++;

        //添加7天的座位数据
        for(int orderDays = 0; orderDays < 7; orderDays++){
            //按座位类型依次拼接串
            while (count <= ANum) {
                ticketSeat = new TicketSeat();
                ticketSeat.setTicketSeat("A","" + count, trainId, calendar.getTime(),"NORMAL");
                ticketSeats.add(ticketSeat);
                count++;
            }
            success = success && ticketSeatService.InsertTicketSeat(ticketSeats);
            ticketSeats = new ArrayList<>();
            count = 1;
            while (count <= BNum) {
                ticketSeat = new TicketSeat();
                ticketSeat.setTicketSeat("B","" + count, trainId, calendar.getTime(),"NORMAL");
                ticketSeats.add(ticketSeat);
                count++;
            }
            success = success && ticketSeatService.InsertTicketSeat(ticketSeats);
            ticketSeats = new ArrayList<>();
            count = 1;
            while (count <= CNum) {
                ticketSeat = new TicketSeat();
                ticketSeat.setTicketSeat("C","" + count, trainId, calendar.getTime(),"NORMAL");
                ticketSeats.add(ticketSeat);
                count++;
            }
            success = success && ticketSeatService.InsertTicketSeat(ticketSeats);
            ticketSeats = new ArrayList<>();
            count = 1;
            //每生成完一天的座位日期加一天
            calendar.add(Calendar.DATE, 1);
        }
        if(!success) {
            model.addAttribute("seatmsg", "座位添加失败");
            return "redirect:/trains";
        }
        else
            model.addAttribute("trainmsg","添加成功");

        return "redirect:/trains";
    }

    //修改列车信息
    @GetMapping("/train/{trainNum}")
    public String toEditTrain(@PathVariable("trainNum") String trainNum,
                              Model model){
        Train train = trainService.FindTrainByNum(trainNum);
        model.addAttribute("train", train);
        List<TrainType> trainTypes = trainTypeService.FindAllTrainTypes();
        List<Station> stations = stationService.FindAllStations();
        Price price = priceService.FindPriceByTrainAndStation(train.getStartStationid(),train.getEndStationid(),train.getTrainTypeId());
        System.out.println(""+price.getAPrice()+price.getBPrice()+price.getCPrice());
        model.addAttribute("trainTypes", trainTypes);
        model.addAttribute("stations", stations);
        model.addAttribute("price", price);

        return "addtrain";
    }


    @PutMapping("/train")
    public String updateTrain(Train train){

        System.out.println("列车数据：" + train);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return "redirect:/trains";
    }

}
