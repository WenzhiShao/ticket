package com.xjtu.happy.ticket.util;

import java.util.Random;

/**
 * 字符串操作工具类
 */
public class StringUtil {
    /**
     * 生成六位随机数
     */
    public static String genSixRandom(){
        Random random = new Random();
        String result ="";
        for (int i=0;i<6;i++){
            result+=random.nextInt(10);
        }
        return result;
    }
}
