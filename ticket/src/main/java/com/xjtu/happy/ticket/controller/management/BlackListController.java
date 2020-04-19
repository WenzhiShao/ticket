package com.xjtu.happy.ticket.controller.management;

import com.xjtu.happy.ticket.bean.BlackList;
import com.xjtu.happy.ticket.service.management.BlackListService;
import com.xjtu.happy.ticket.service.management.UsersService;
import com.xjtu.happy.ticket.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BlackListController {

    @Autowired
    UsersService usersService;
    @Autowired
    BlackListService blackListService;

    @GetMapping("/user")
    public String userlist(Model model){
        List<User> users = usersService.findAllUser();
        model.addAttribute("users",users);
        return "user";
    }

    @GetMapping("/user/{userId}")
    public String addBlackList(@PathVariable("userId") int userId, BlackList blackList){
        User user = usersService.findUser(userId);
     //   BlackList blackList = new BlackList();
        blackList.setUserName(user.getUserName());
        blackList.setPassword(user.getPassword());
        blackList.setName(user.getName());
        blackList.setIdentityNum(user.getIdentityNum());
        blackListService.insertUser(blackList);
        return "redirect:/user";

    }

    @GetMapping("/blacklist")
    public String blacklist(Model model){
        List<BlackList> blackLists = blackListService.findAll();
        model.addAttribute("blackLists",blackLists);
        return "blacklist";
    }

    //移出黑名单
    @GetMapping("/deleteUser/{id}")
    public String deleteManager(@PathVariable("id") int id){
        blackListService.delete(id);
        return "redirect:/blacklist";
    }


}
