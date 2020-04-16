package com.xjtu.happy.ticket.controller.login;

import com.xjtu.happy.ticket.service.login.FindPassService;
import com.xjtu.happy.ticket.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class changepwController {
    @RequestMapping("/changepw")
    public String changepw(){
        return "changepw";
    }
    @Autowired
    FindPassService findPassService;
    @RequestMapping(value = "/change")
    public String change(HttpServletRequest request , HttpSession session, HttpServletResponse response){
        String mailCode = request.getParameter("mailCode");
        String password = request.getParameter("password");
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
        String username = (String) request.getSession().getAttribute("changeUser");
        Cookie[] cookies = request.getCookies();
        for (Cookie c:cookies){
            if (c.getName().equals("mailCode")){
                if(c.getValue().equals(mailCode)){
                    if(findPassService.changepw(username,md5password)) {
                        session.setAttribute("msgOfLogin", "修改成功");
                        return "redirect:/login";
                    }
                }
            }
        }
        session.setAttribute("msgOfChangePw","修改失败，请重新验证");
        return "redirect:/changepw";
    }

    @Resource
    private JavaMailSenderImpl mailSender;
    @RequestMapping(value = "/semagain")
    public String semagain(HttpServletRequest request , HttpSession session, HttpServletResponse response){
        //获取用户名
        String userName = (String) request.getSession().getAttribute("changeUser");
        //获取邮箱地址
        String nowEmail= findPassService.getEmail(userName);
        //生成验证码
        String mailCode= StringUtil.genSixRandom();

        //发送邮件
        SimpleMailMessage message2 = new SimpleMailMessage();    //消息构造器
        message2.setFrom("247552025@qq.com");                    //发件人
        message2.setTo(nowEmail);                                //收件人
        message2.setSubject("快乐购票系统--用户密码找回");        //主题
        //正文
        message2.setText("尊敬的用户"+userName+"您好，您的验证码为："+mailCode+"。如非您本人操作，请忽略此邮件");
        mailSender.send(message2);
        System.out.println(mailCode);
        //将验证码存在cookie中
        Cookie mailCodeCookie = new Cookie("mailCode", mailCode);
        mailCodeCookie.setMaxAge(60*5);//保存5min
        mailCodeCookie.setPath("/");//所有路径
        response.addCookie(mailCodeCookie);
        return "redirect:/changepw";
    }
}
