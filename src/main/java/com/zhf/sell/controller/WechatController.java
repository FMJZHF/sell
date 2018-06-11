package com.zhf.sell.controller;

import com.google.common.base.Utf8;
import com.zhf.sell.config.ProjectUrlConfig;
import com.zhf.sell.config.WechatMpConfig;
import com.zhf.sell.enums.ResultEnum;
import com.zhf.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;

//@RestController   // 返回json格式的   不会进行重定向跳转 必须使用 @Controller
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WechatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置(在WechatMpConfig 已经完成)
        //2.调用方法
        String url = projectUrlConfig.getWechatMpAuthorize() + "/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_BASE, URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code，redirectUrl={}", redirectUrl);
        return "redirect:" + redirectUrl; // 地址重定向
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】[]", e);
            e.printStackTrace();
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】获取openId，openId={}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId; // 地址重定向
    }

    //https://open.weixin.qq.com/connect/qrconnect?appid=wx6ad144e54af67d87&redirect_uri=http://sell.springboot.cn/sell/qr/oTgZpwY02TMCNMprUSJ2YFTtm5_w&&scope=snsapi_login

    @GetMapping("/qrAuthorize")
    public String qrAuthorize(@RequestParam("returnUrl") String returnUrl) {
        //1.配置(在WechatMpConfig 已经完成)
        //2.调用方法
        String url = projectUrlConfig.getWechatOpenAuthorize() + "/sell/wechat/qrUserInfo";
        String redirectUrl = wxOpenService.buildQrConnectUrl(url,
                WxConsts.QrConnectScope.SNSAPI_LOGIN,
                URLEncoder.encode(returnUrl));
        log.info("【微信网页授权】获取code，redirectUrl={}", redirectUrl);
        return "redirect:" + redirectUrl; // 地址重定向
    }

    @GetMapping("/qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code
//                             ,@RequestParam("state") String returnUrl
                            ) {
        //TODO 这是借用的账号，如果不是借用的，直接放开上面的注释
        String returnUrl ="http://zhf.s1.natapp.cc/sell/seller/login";
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.info("【微信网页授权】[]", e);
            e.printStackTrace();
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("【微信网页授权】获取openId，openId={}", openId);
        return "redirect:" + returnUrl + "?openid=" + openId; // 地址重定向
    }


}
