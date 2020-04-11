package com.xjtu.happy.ticket.component;

import com.xjtu.happy.ticket.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class LoginHandlerInterceptor implements HandlerInterceptor{
    @Autowired
    LoginService userService;
    public LoginHandlerInterceptor(LoginService userService){
        this.userService=userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            //session未存储用户信息，检查自定义的cookie
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("userName")) {
                        String name = c.getValue();
                        Boolean filename = userService.FindByname(name);
                        if (filename) {
                            return true;//放行
                        }
                    }
                }
            }
                request.setAttribute("msg", "没有权限，请先登录");
                request.getRequestDispatcher("/index.html").forward(request, response);
                return false;
        } else {
            //session定义了用户信息，放行
            return true;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        System.out.println("postCompletion----"+ user +" ::: "+ request.getRequestURL());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Object user = request.getSession().getAttribute("userName");
        System.out.println("afterCompletion----"+ user +" ::: "+ request.getRequestURL());
    }
}