package com.xjtu.happy.ticket.controller.login;

import com.xjtu.happy.ticket.config.MyMvcConfig;
import com.xjtu.happy.ticket.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;
import java.util.Map;

import static com.xjtu.happy.ticket.config.MyMvcConfig.*;

@Controller
public class LoginController {
    @Autowired
    LoginService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping({"/index","/","/index.html"})
    public String index(){
        return "login";
    }
    @RequestMapping(value = "/user/login")
    public String addlogin(HttpServletRequest request , Model model, Map<String,Object> map,HttpSession session,HttpServletResponse response){
        //获取信息
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
        session.setAttribute("loginUser",userName);
        boolean user =userService.Identity(userName,md5password);
        boolean player =userService.Check(userName);
        //是否在数据库中
        if (user){
            //把用户信息写入cookie
            Cookie usernameCookie = new Cookie("userName",userName);
            usernameCookie.setMaxAge(60*60);//保存一个小时
            usernameCookie.setPath("/");//所有路径
            response.addCookie(usernameCookie);
            //判断是否是管理员
            if(!player){
                //普通用户
                model.addAttribute("user",userName);
                map.put("msg","登录成功");
                return "redirect:/search.html";
            }
            else {
                //是管理员
                return "redirect:/admin.html";
        }
        //登录失败，返回登录页面
        else{
            map.put("msg","登录失败");
            return "login";
        }

    }

}



