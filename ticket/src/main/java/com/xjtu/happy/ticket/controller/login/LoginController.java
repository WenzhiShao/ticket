package com.xjtu.happy.ticket.controller.login;

import com.xjtu.happy.ticket.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    LoginService userService;

    @RequestMapping({"/","/index.html","/index"})
    public String Index(){
        return "login";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping(value = "/user/login")
    public String addlogin(HttpServletRequest request , Model model, Map<String,Object> map,HttpSession session){
        //获取信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String md5passworld = DigestUtils.md5DigestAsHex(password.getBytes());
        session.setAttribute("loginUser",userName);
        boolean user =userService.Identity(userName,md5passworld);
        boolean player =userService.Check(userName);
        //是否在数据库中
        if (user==true){
            //判断是否是管理员
            if(player==false){
                model.addAttribute("user",userName);
                map.put("msg","登录成功");
                return "redirect:/search";}
            else {
                return "redirect:/trains";
            }
        }
        //登录失败，返回登录页面
        else{
            map.put("msg","登录失败");
            return "login";
        }
    }
}



