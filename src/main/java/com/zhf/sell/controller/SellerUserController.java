package com.zhf.sell.controller;

import com.lly835.bestpay.rest.type.Get;
import com.zhf.sell.config.ProjectUrlConfig;
import com.zhf.sell.constant.CookieConstant;
import com.zhf.sell.constant.RedisConstant;
import com.zhf.sell.dataobject.SellerInfo;
import com.zhf.sell.enums.ResultEnum;
import com.zhf.sell.service.SellerService;
import com.zhf.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 卖家用户相关操作
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        //1.openid 去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (null == sellerInfo) {
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token至 redis
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConstant.EXPIRE;
        //redis的key值 ， redis存储额值 value， 过期时间，时间单位（s）
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid ,expire ,TimeUnit.SECONDS);
        //3.设置token至cookie
        CookieUtil.set(response , CookieConstant.TOKEN,openid , expire);


        return new ModelAndView("redirect:"+projectUrlConfig.getSell()+"/sell/seller/order/list");
    }

    @GetMapping("/logout")
    public void logout() {

    }

}
