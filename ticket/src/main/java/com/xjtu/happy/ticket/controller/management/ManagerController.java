package com.xjtu.happy.ticket.controller.management;

import com.xjtu.happy.ticket.service.login.RegisterService;
import com.xjtu.happy.ticket.service.management.UsersService;
import com.xjtu.happy.ticket.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ManagerController {

    @Resource
    @Autowired
    UsersService usersService;

    @Autowired
    RegisterService registerService;

    @GetMapping("/manager")
    public String managerList(Model model){
        List<User> admins = usersService.findAllAdmin();
        model.addAttribute("admins",admins);
        return "manager";
    }

    //删除管理员
    @GetMapping("/delete/{userId}")
    public String deleteManager(@PathVariable("userId") int userId){
        usersService.deleteAdmin(userId);
        return "redirect:/manager";
    }

       //页面跳转
        @GetMapping("/registers")
        public String Register(){
            return "registers";
        }

        //管理员注册
        @RequestMapping("/admin1")
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
            user.setType("Admin");
            if(registerService.save(user)) {
                //  return "redirect:/trains";
                return "redirect:/manager";

            }
            else
            {
                return "registers";
            }
        }

}
