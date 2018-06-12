package com.zhf.sell.service;

import com.zhf.sell.dto.OrderDTO;

/**
 * 消息模板推送
 */
public interface PushMessageService {
    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);
}
