package com.zhf.sell.service.impl;

import com.zhf.sell.dataobject.OrderDetail;
import com.zhf.sell.dto.OrderDTO;
import com.zhf.sell.enums.OrderStatusEnum;
import com.zhf.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final String BUYE_OPENID = "oTgZpwY02TMCNMprUSJ2YFTtm5_w";

    private final String ORDERID = "1527469958664185683";

    @Test
    public void create() {
        OrderDTO  orderDTO = new OrderDTO();
//        orderDTO.setBuyerName("大海");
//        orderDTO.setBuyerName("山川");
//        orderDTO.setBuyerName("战虎额");
//        orderDTO.setBuyerName("阿斯顿");
        orderDTO.setBuyerName("时光飞逝");
        orderDTO.setBuyerAddress("亚麦国际中心");
        orderDTO.setBuyerPhone("13542443421");
        orderDTO.setBuyerOpenid(BUYE_OPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
//        o1.setProductId("1234568");
//        o1.setProductId("123456");
        o1.setProductId("123457");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);
        orderDTO.setOrderDetailList(orderDetailList);

        OrderDTO result = orderService.create(orderDTO);
        log.info("[创建订单]，result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() {
        OrderDTO result = orderService.findOne(ORDERID);
        log.info("[获取订单详情]，result={}",result);
        Assert.assertEquals(ORDERID , result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request = new PageRequest( 0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(BUYE_OPENID ,request);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANNEL.getCode() , result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode() , result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDERID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode() , result.getPayStatus());
    }

    @Test
    public void list() {

        PageRequest request = new PageRequest( 0,2);
        Page<OrderDTO> orderDTOPage=orderService.findList(request);
//        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
        Assert.assertTrue("查询所有的订单列表",orderDTOPage.getTotalElements()>0);
    }
}