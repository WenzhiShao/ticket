package com.xjtu.happy.ticket.controller.order;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.xjtu.happy.ticket.bean.Orders;
import com.xjtu.happy.ticket.config.AlipayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class AlipayController {
    @Autowired
    AlipayConfig alipayConfig;
    @RequestMapping("/alipay")
    public Object alipay(HttpServletRequest request,HttpServletResponse response) throws Exception {
        //生成车票
        HttpSession session = request.getSession();
        Orders order = (Orders) session.getAttribute("order");
        //付款金额（必填）
        //可能是通过座位等级在session查金额
        String payables = order.getTotalPrice().toString();
        //订单名称 （必填）
        //可以考虑用用火车路线号代替
        String subject = String.valueOf(order.getTrainId());
        // 商品描述
        String body = "快乐订票";
        // 获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(
                alipayConfig.getURL(),
                alipayConfig.getAPPID(),
                alipayConfig.getRSA_PRIVATE_KEY(),
                alipayConfig.getFORMAT(),
                alipayConfig.getCHARSET(),
                alipayConfig.getALIPAY_PUBLIC_KEY(),
                alipayConfig.getSIGNTYPE());
        // 设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(alipayConfig.getReturn_url());  //设置支付成功的回显页面
        alipayRequest.setNotifyUrl(alipayConfig.getNotify_url());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = sdf.format(new Date());
        // 付款金额，必填
        String total_amount = payables.replace(",", "");
        /**
         * out_trade_no 唯一订单号
         * total_amount 付款金额
         * subject 订单名称
         * body 商品描述，可以设置为公司+内容
         * product_code 销售产品码 这里表示为 电脑网页支付
         */
        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setSubject(subject);
        model.setOutTradeNo(out_trade_no);
        model.setTimeoutExpress("30m");
        model.setTotalAmount(total_amount);
        model.setProductCode("FAST_INSTANT_TRADE_PAY");
        alipayRequest.setBizModel(model);
        // 请求
        //result 为二维码页面的html
        String result = alipayClient.pageExecute(alipayRequest).getBody();
        // System.out.println(result);
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
//        AlipayConfig.logResult(result);// 记录支付日志
        //设置类型
        response.setContentType("text/html; charset=UTF-8");
        //将result打印输出
        PrintWriter out = response.getWriter();
        out.print(result);
        return null;
    }
}