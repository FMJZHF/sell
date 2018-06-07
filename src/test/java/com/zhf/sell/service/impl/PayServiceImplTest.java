package com.zhf.sell.service.impl;

import com.zhf.sell.dto.OrderDTO;
import com.zhf.sell.service.OrderService;
import com.zhf.sell.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        String orderId= "1528091290037500327";
        OrderDTO orderDTO = orderService.findOne(orderId);
        payService.create(orderDTO);
    }
    @Test
    public void refund() {
        String orderId= "1528161614600873493";
        OrderDTO orderDTO = orderService.findOne(orderId);
        payService.refund(orderDTO);
    }

}