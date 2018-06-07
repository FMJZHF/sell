package com.zhf.sell.utils;

import java.util.Random;


public class KeyUtil {

    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * synchronized ： 解决多线程问题
     * @return
     */
    public static synchronized String getUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000; //生成6位随机数
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
