package com.xjtu.happy.ticket.controller.login;
import com.xjtu.happy.ticket.bean.User;


import com.xjtu.happy.ticket.mapper.login.RegisterMapper;
import com.xjtu.happy.ticket.service.login.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    @Resource
    @Autowired
    RegisterService registerService;

    @GetMapping("/register")
    public String Register(){
        return "register";
    }

    @RequestMapping("/addUser")
    public String register(HttpServletRequest request){
        String username =request.getParameter("username");
        String password =request.getParameter("password");
        String md5passworld = DigestUtils.md5DigestAsHex(password.getBytes());
        String email =request.getParameter("email");
        String name =request.getParameter("name");
        String identityNum=request.getParameter("identityNum");
        String phone=request.getParameter("phone");
        User user=new User();
        user.setUserName(username);
        user.setPassword(md5passworld);
        user.setEmail(email);
        user.setPhone(phone);
        user.setName(name);
        user.setIdentityNum(identityNum);
        user.setType("user");//默认注册用户为购物人员
        if(registerService.save(user)) {
            return "login";
        }
        else
        {
            return "register";
        }
    }

}
