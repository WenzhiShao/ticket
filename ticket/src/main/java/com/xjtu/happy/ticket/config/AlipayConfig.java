package com.xjtu.happy.ticket.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pay.alipay")
public class AlipayConfig {
    // 商户appid
    private String APPID;

    // 私钥 pkcs8格式的
    private String RSA_PRIVATE_KEY;

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public void setRSA_PRIVATE_KEY(String RSA_PRIVATE_KEY) {
        this.RSA_PRIVATE_KEY = RSA_PRIVATE_KEY;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public void setCHARSET(String CHARSET) {
        this.CHARSET = CHARSET;
    }

    public void setFORMAT(String FORMAT) {
        this.FORMAT = FORMAT;
    }

    public void setALIPAY_PUBLIC_KEY(String ALIPAY_PUBLIC_KEY) {
        this.ALIPAY_PUBLIC_KEY = ALIPAY_PUBLIC_KEY;
    }

    public void setLog_path(String log_path) {
        this.log_path = log_path;
    }

    public void setSIGNTYPE(String SIGNTYPE) {
        this.SIGNTYPE = SIGNTYPE;
    }

    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    private String notify_url;
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    private String return_url;
    // 请求网关地址
    private String URL;
    // 编码
    private String CHARSET;

    public String getAPPID() {
        return APPID;
    }

    public String getRSA_PRIVATE_KEY() {
        return RSA_PRIVATE_KEY;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public String getURL() {
        return URL;
    }

    public String getCHARSET() {
        return CHARSET;
    }

    public String getFORMAT() {
        return FORMAT;
    }

    public String getALIPAY_PUBLIC_KEY() {
        return ALIPAY_PUBLIC_KEY;
    }

    public String getLog_path() {
        return log_path;
    }

    public String getSIGNTYPE() {
        return SIGNTYPE;
    }

    // 返回格式
    private String FORMAT = "json";
    // 支付宝公钥
    private String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrEVFMOSiNJXaRNKicQuQdsREraftDA9Tua3WNZwcpeXeh8Wrt+V9JilLqSa7N7sVqwpvv8zWChgXhX/A96hEg97Oxe6GKUmzaZRNh0cZZ88vpkn5tlgL4mH/dhSr3Ip00kvM4rHq9PwuT4k7z1DpZAf1eghK8Q5BgxL88d0X07m9X96Ijd0yMkXArzD7jg+noqfbztEKoH3kPMRJC2w4ByVdweWUT2PwrlATpZZtYLmtDvUKG/sOkNAIKEMg3Rut1oKWpjyYanzDgS7Cg3awr1KPTl9rHCazk15aNYowmYtVabKwbGVToCAGK+qQ1gT3ELhkGnf3+h53fukNqRH+wIDAQAB";
    // 日志记录目录
    private String log_path = "/log";
    // RSA2
    private String SIGNTYPE = "RSA2";

//	private static void logResult(String sWord) {
//		FileWriter writer = null;
//		try {
//			writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//			writer.write(sWord);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (writer != null) {
//				try {
//					writer.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}

}
