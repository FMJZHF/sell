package com.zhf.sell.service;

import com.zhf.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * redis分布式锁
 */
@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间 +  超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        //相当于 redis命令行 的 setnx
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            //如果成功设置
            return true;
        }
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && Long.valueOf(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(currentValue) && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {


            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常，{}", e);
        }
    }


    /**
     * 使用方式： 在 秒杀类外部定义
     */

    @Autowired
    private RedisLock  redisLock;
    private static final int TIMEOUT= 10 * 1000; //超时时间 10s

    //在秒杀方法内
    public void  orderMock(String productId){
        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
       if(!redisLock.lock(productId , String.valueOf(time))){
          throw new SellException(101 , "秒杀人数太多,换个姿势试试");
       }

       //秒杀逻辑  ....

        //解锁
        redisLock.unlock(productId , String.valueOf(time));

    }
}
