package com.xjtu.happy.ticket.controller.login;

import com.xjtu.happy.ticket.bean.User;
import com.xjtu.happy.ticket.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class userMessageController {
    @Autowired
    LoginService loginService;
    @GetMapping("/usermessage")
    public String usermessage( HttpServletRequest request, Model model){
        Cookie[] cookies = request.getCookies();
        for(Cookie c:cookies){
            if(c.getName().equals("userName")){
                String loginUser=c.getValue();
                User user= loginService.GetUserByname(loginUser);
                model.addAttribute("loginUser", user);
            }
        }

        return "usermessage";
    }


    @RequestMapping(value = "/changeMessage")
    public String changeMessage(HttpServletRequest request, HttpSession session){
        session.removeAttribute("msgOfUpdate");
        String password = request.getParameter("password");         //获取密码
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes()); //将密码加密
        String name = request.getParameter("name");                 //获取姓名
        String identityNum = request.getParameter("identityNum");   //获取身份证信息
        String phone = request.getParameter("phone");               //获取电话
        String email = request.getParameter("email");               //获取邮箱

        String userName=(String) request.getSession().getAttribute("loginUser");   //获取用户名

        if( !(name.equals("") && identityNum.equals("")
            && phone.equals("") && email.equals("") ))
        {
            if(loginService.updateUser(userName,name,identityNum,phone,email) <= 0){
                session.setAttribute("msgOfUpdate","信息更新失败");
                return "redirect:/usermessage";
            }
        }

        if(!password.equals("")){                                                       //更改密码
            if(!loginService.updatePassword(userName,md5password)){
                session.setAttribute("msgOfUpdate","密码更新失败");
                return "redirect:/usermessage";
            }
        }
        return "redirect:/search";
    }

}
