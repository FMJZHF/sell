package com.zhf.sell.aspect;

import com.zhf.sell.constant.CookieConstant;
import com.zhf.sell.constant.RedisConstant;
import com.zhf.sell.exception.SellException;
import com.zhf.sell.exception.SellerAuthorizeException;
import com.zhf.sell.utils.CookieUtil;
import com.zhf.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * AOP
 * 卖家前端验证
 */
@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.zhf.sell.controller.Seller*.*(..))" +
            "&& !execution(public * com.zhf.sell.controller.SellerUserController.*(..))")
    public void verify() {

    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //url
//        log.info("url={}",request.getRequestURL());
        //method
//        log.info("method={}",request.getMethod());
        //ip
//        log.info("ip={}",request.getRemoteAddr());


        //查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if (null == cookie) {
            log.warn("【登录校验】Cookie中查不到token");
            throw new SellerAuthorizeException();
        }
        //去redis里查询
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登录校验】redis中查不到token");
            throw new SellerAuthorizeException();

        }
    }
}
