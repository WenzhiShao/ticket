package com.xjtu.happy.ticket.controller.login;


import com.xjtu.happy.ticket.service.login.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;


@Controller
public class AjaxController {
    @Resource
    @Autowired
    RegisterService registerService;
/*
    ajax判断用户名是否存在
 */
    @RequestMapping("/register")
    @ResponseBody
   public String ajax1(String name){
        String msg="";
        System.out.println(name);
        if(!registerService.IsUserExit(name))
            msg="√";
         else
             msg="用户名已存在";
        return msg;
//        User user=userMapper.check(name);
//        System.out.println(user);
//        if(name!=null){
//            if (user!=null)
//            {
//                msg="ok";
//            }
//            else{
//                msg="用户名已存在";
//            }
//        }
   }
}
