package com.zhf.sell.service.impl;

import com.zhf.sell.dataobject.ProductInfo;
import com.zhf.sell.repository.ProductInfoRepository;
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
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductServiceImplTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    public void findOne() {
        ProductInfo productInfo = productService.findOne("123456");
        Assert.assertEquals("123456", productInfo.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfos = productService.findUpAll();
        Assert.assertNotEquals(0, productInfos.size());
    }

    @Test
    public void findAll() {
        PageRequest request = new PageRequest(0, 2);
        Page<ProductInfo> productInfos = productService.findAll(request);
        log.info("总元素：{}", productInfos.getTotalElements());
        Assert.assertNotEquals(0, productInfos.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo info = new ProductInfo();
        info.setProductId("1234567");
        info.setProductName("糖醋鲤鱼");
        info.setProductPrice(new BigDecimal(32.89));
        info.setProductStock(100);
        info.setProductDescription("甜而不腻");
        info.setProductIcon("http://xxx.jpg");
        info.setProductStatus(0);
        info.setCategoryType(1);
        ProductInfo result = productService.save(info);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale() {
       String productId = "123456" ;

        ProductInfo result = productService.onSale(productId);
//        ProductInfo result = productService.offSale(productId);
        Assert.assertNotNull(result);
    }
}