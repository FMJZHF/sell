package com.zhf.sell.repository;

import com.zhf.sell.dataobject.OrderMaster;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    private final String OPENID = "110110";

    @Test
    public void saveTest() {

        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("师兄");
        orderMaster.setBuyerPhone("123891829301");
        orderMaster.setBuyerAddress("幕课网");
        orderMaster.setBuyerOpenid(OPENID);
        orderMaster.setOrderAmount(new BigDecimal(9.3));
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);

    }



    @Test
    public void findByBuyerOpenid() {

        PageRequest request = PageRequest.of(1,3);
        Page<OrderMaster> orderMasters = repository.findByBuyerOpenid(OPENID , request);
        log.info("总元素：{}", orderMasters.getTotalElements());
        Assert.assertNotEquals(0, orderMasters.getTotalElements());
    }
}