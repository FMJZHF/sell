package com.zhf.sell.repository;

import com.sun.org.apache.regexp.internal.RE;
import com.zhf.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest() {
        ProductInfo info = new ProductInfo();
        info.setProductId("1234");
        info.setProductName("蛋炒饭");
        info.setProductPrice(new BigDecimal(12));
        info.setProductStock(100);
        info.setProductDescription("很香");
        info.setProductIcon("http://xxx.jpg");
        info.setProductStatus(0);
        info.setCategoryType(1);
        ProductInfo result = repository.save(info);
        Assert.assertNotNull(result);
    }
    @Test
    public void findByProductStatus() {
        //查询上架商品 0：正常(上架)
        List<ProductInfo> productInfos= repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfos.size());
    }
}