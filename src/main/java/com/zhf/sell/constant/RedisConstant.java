package com.zhf.sell.constant;

/**
 * redis常量
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s"; //设置常量

    Integer EXPIRE = 7200;//2个小时，设置过期时间
}
