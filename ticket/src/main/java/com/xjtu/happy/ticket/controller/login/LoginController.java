package com.xjtu.happy.ticket.controller.login;

import com.xjtu.happy.ticket.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



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
    @GetMapping({"/login","/"})
    public String login(HttpServletRequest req){
        Object o = req.getSession().getAttribute("exit");
        if(o == null)
            return "/login";
        int exit = (int )o;
        if(exit == 2) {
            HttpSession session = req.getSession();
            if (session.getAttribute("msgOfLogin") != null) {
                session.removeAttribute("msgOfLogin");
                session.removeAttribute("exit");
            }
        }
        if(exit == 1)
            req.getSession().setAttribute("exit",2);
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
        boolean user =userService.Identity(userName,md5password);
        boolean player =userService.Check(userName);
        boolean activated = userService.activated(userName);
        //是否在数据库中
        if (user) {
            //检查用户激活状态
            if (!activated) {
                session.setAttribute("msgOfLogin", "没有权限");
                session.setAttribute("exit", 1);
                return "redirect:/login";
            } else {
                //用户信息写到session里
                session.setAttribute("loginUser", userName);
                //把用户信息写入cookie
                Cookie usernameCookie = new Cookie("userName", userName);
                usernameCookie.setMaxAge(60 * 15);//保存15min
                usernameCookie.setPath("/");//所有路径
                response.addCookie(usernameCookie);
                //判断是否是管理员
                if (!player) {
                    //普通用户
                    model.addAttribute("user", userName);
                    map.put("msg", "登录成功");
                    return "redirect:/search";
                } else {
                    //是管理员
                    return "redirect:/trains";
                }
            }
        }
        //登录失败，返回登录页面
        else{
            session.setAttribute("msgOfLogin","登录失败");
            session.setAttribute("exit",1);
            return "redirect:/login";
        }

    }

}



