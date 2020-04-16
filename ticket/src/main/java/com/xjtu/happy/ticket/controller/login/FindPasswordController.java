package com.xjtu.happy.ticket.controller.login;

import com.xjtu.happy.ticket.service.login.FindPassService;
import com.xjtu.happy.ticket.service.login.LoginService;
import com.xjtu.happy.ticket.util.StringUtil;
import com.xjtu.happy.ticket.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

@Controller
public class FindPasswordController {
    @RequestMapping("/findpassword")
    public String index(){
        return "findpassword";
    }
    @RequestMapping("/getVerifyCode")
    public void getVerifyCode(HttpServletRequest request, HttpServletResponse response){
        // 调用工具类生成的验证码和验证码图片
        Map<String, Object> codeMap = VerifyCodeUtil.generateCodeAndPic(150,50,48);

        // 将四位数字的验证码保存到Session中。
        HttpSession session = request.getSession();
        session.setAttribute("verifyCode", codeMap.get("code").toString().toUpperCase());

        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", -1);

        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos;
        try {
            sos = response.getOutputStream();
            ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
            sos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查用户名与验证码
     * 发送邮件
     */

    @Autowired
    FindPassService findPassService;
    @Resource
    private JavaMailSenderImpl mailSender;
        @RequestMapping(value = "/sendEmail")
        public String checkUserName(HttpServletRequest request , HttpSession session, HttpServletResponse response){
            String userName = request.getParameter("userName");
            String verifyCode = request.getParameter("verify-code");
            String sessionVerifyCode = (String) request.getSession().getAttribute("verifyCode");
            if(!findPassService.checkUser(userName)){
                session.setAttribute("msgOffindpass","用户名不存在，请重新输入");
                return "redirect:/findpassword";
            }else if (!sessionVerifyCode.equals(verifyCode.toUpperCase())){
        session.setAttribute("msgOffindpass","验证码错误");
        return "redirect:/findpassword";}
            else {
                //将用户名写入session
                session.setAttribute("changeUser",userName);
                //获取邮箱地址
                String nowEmail= findPassService.getEmail(userName);
                //生成验证码
                String mailCode= StringUtil.genSixRandom();

                //发送邮件
                SimpleMailMessage message = new SimpleMailMessage();    //消息构造器
                message.setFrom("247552025@qq.com");                    //发件人
                message.setTo(nowEmail);                                //收件人
                message.setSubject("快乐购票系统--用户密码找回");        //主题
                //正文
                message.setText("尊敬的用户"+userName+"您好，您的验证码为："+mailCode+"。如非您本人操作，请忽略此邮件");
                mailSender.send(message);
                System.out.println(mailCode);
                //将验证码存在cookie中
                Cookie mailCodeCookie = new Cookie("mailCode", mailCode);
                mailCodeCookie.setMaxAge(60*5);//保存5min
                mailCodeCookie.setPath("/");//所有路径
                response.addCookie(mailCodeCookie);
                return "redirect:/changepw";
            }
        }

}
