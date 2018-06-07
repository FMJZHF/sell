package com.zhf.sell.service;

import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import com.zhf.sell.dto.OrderDTO;

/**
 * 支付
 */
public interface PayService {

    //创建订单。发起支付
    PayResponse create(OrderDTO orderDTO);

    //微信异步通知
    PayResponse notify(String notifyData);

    //微信退款
    RefundResponse refund(OrderDTO orderDTO);
}
