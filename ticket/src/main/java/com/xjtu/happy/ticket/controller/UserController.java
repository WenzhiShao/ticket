package com.xjtu.happy.ticket.controller;

import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/user/{id}")
    public String findUserById(Model m,@PathVariable("id") String id) {
        User user = userService.FindUserByID(id);
        m.addAttribute("user",user);
        //返回视图解析器xxx
        return "index";
    }
}
