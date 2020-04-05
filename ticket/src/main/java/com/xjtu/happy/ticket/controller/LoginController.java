package com.xjtu.happy.ticket.controller;

import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Resource
    private UserMapper userMapper;

   @RequestMapping({"/","/index.html","/index"})
    public String Index(){
        return "login";
    }
    @GetMapping("/login")
    public String login(){
       return "login";

    }    @RequestMapping(value = "/user/login")
    public String addlogin(HttpServletRequest request , Model model, Map<String,Object> map,HttpSession session){

        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String md5passworld = DigestUtils.md5DigestAsHex(password.getBytes());
        session.setAttribute("loginUser",userName);
        User user =userMapper.Identity(userName,md5passworld);
        User player =userMapper.Check(userName);

        if (user!=null){
            if(player==null){
                model.addAttribute("user",userName);
                map.put("msg","登录成功");
                return "firstpage";}
            else {
                return "admin";
            }
        }
        else{
            map.put("msg","登录失败");
            return "login";
        }
    }
}


