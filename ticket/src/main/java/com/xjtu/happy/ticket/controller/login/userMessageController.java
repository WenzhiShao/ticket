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
        String password = request.getParameter("password");         //获取密码
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes()); //将密码加密
        String name = request.getParameter("name");                 //获取姓名
        String identityNum = request.getParameter("identityNum");   //获取身份证信息
        String phone = request.getParameter("phone");               //获取电话
        String email = request.getParameter("email");               //获取邮箱

        String userName=(String) request.getSession().getAttribute("loginUser");   //获取用户名


        if(!password.equals("")){                                                       //更改密码
            if(!loginService.updatePassword(userName,md5password)){
                session.setAttribute("msgOfUpdate","密码更新失败");
                return "redirect:/usermessage";
            }
        }
        if(!name.equals("")){                                                           //更改姓名
            if(!loginService.updateName(userName,name)){
                session.setAttribute("msgOfUpdate","姓名更新失败");
                return "redirect:/usermessage";
            }
        }
        if(!identityNum.equals("")){                                                    //更改身份证号
            if(!loginService.updateIdentityNum(userName,identityNum)){
                session.setAttribute("msgOfUpdate","身份证号更新失败");
                return "redirect:/usermessage";
            }
        }
        if(!phone.equals("")){                                                          //更改电话
            if(!loginService.updatePassword(userName,phone)){
                session.setAttribute("msgOfUpdate","电话更新失败");
                return "redirect:/usermessage";
            }
        }
        if(!email.equals("")){                                                          //更改邮件
            if(!loginService.updateEmail(userName,email)){
                session.setAttribute("msgOfUpdate","邮箱更新失败");
                return "redirect:/usermessage";
            }
        }
        return "redirect:/search";
    }

}
