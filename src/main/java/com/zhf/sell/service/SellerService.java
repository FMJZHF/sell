package com.zhf.sell.service;

import com.zhf.sell.dataobject.SellerInfo;

/**
 * 卖家端 Service
 */
public interface SellerService {

    /**
     * 通过openid查询卖家端信息
     * @param openId
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openId);
}
