package com.zhf.sell.controller;

import com.lly835.bestpay.rest.type.Get;
import com.zhf.sell.dataobject.SellerInfo;
import com.zhf.sell.enums.ResultEnum;
import com.zhf.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 卖家用户相关操作
 */
@Controller
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerService sellerService;


    @GetMapping
    public ModelAndView login(@RequestParam("openid") String openid,
                              Map<String, Object> map) {
        //1.openid 去和数据库里的数据匹配
        SellerInfo sellerInfo = sellerService.findSellerInfoByOpenid(openid);
        if (null == sellerInfo) {
            map.put("msg", ResultEnum.LOGIN_FAIL);
            map.put("url", "/sell/seller/order/list");
            return new ModelAndView("common/error");
        }
        //2.设置token至 redis

        //3.设置token至cookie
        return new ModelAndView("common/success");
    }

    @GetMapping("/logout")
    public void logout() {

    }

}
