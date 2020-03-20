package com.kalic.redapple.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Kalic Li
 * @ClassName Buildno
 * @Package com.kalic.redapple.utils
 * @Description 根据日期生成 Regno
 * @date 2020/2/13 9:25
 */
public class Buildno {
    /**
     * 生成 Reg 订单编号
     * @param isGroup
     * @return
     */
    public static String buildRegnoPref(String isGroup){
        // 1. 散客预定还是团队预定
        char flag = isGroup.equals("0") ?  'G' : 'T';
        // 2. 获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(date);
        return flag + now;
    }

    /**
     * 生成 预定 订单编号
     * @param isGroup
     * @return
     */
    public static String buildBookingnoPref(String isGroup){
        // 1. 散客预定还是团队预定
        String flag = isGroup.equals("0") ? "BG" : "BT";
        // 2. 获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(date);
        return flag + now;
    }

    /**
     * 生成 payno 支付 编号
     * @return
     */
    public static String buildPaynoPref(){
        // 1. 账单flag
        String flag = "P";
        // 2. 获取当前时间
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String now = dateFormat.format(date);
        return flag + now;
    }

    // 补0
    public static String zeroRegno(int maxno){
        if (maxno > 100000){
            return maxno + "";
        }else if (maxno > 10000){
            return "0" + maxno;
        }else if (maxno > 1000){
            return "00" + maxno;
        }else if (maxno > 100){
            return "000" + maxno;
        }else if (maxno > 10){
            return "0000" + maxno;
        }else{
            return "00000" + maxno;
        }
    }

    public static void main(String args[]){
        System.out.println(buildRegnoPref("0"));
        System.out.println(buildRegnoPref("1"));
        System.out.println(buildBookingnoPref("0"));
        System.out.println(buildBookingnoPref("1"));
        System.out.println(zeroRegno(1));
        System.out.println(zeroRegno(22));
        System.out.println(zeroRegno(434));
        System.out.println(zeroRegno(6541));
        System.out.println(zeroRegno(78294));
        System.out.println(zeroRegno(989919));
        System.out.println(buildBookingnoPref("1") + zeroRegno(22));
    }
}
