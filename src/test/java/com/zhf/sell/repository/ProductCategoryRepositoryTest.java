package com.zhf.sell.repository;

import com.zhf.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest() {
        ProductCategory productCategory = repository.findById(1).get();
        log.info(productCategory.toString());
    }

    @Test
//    @Transactional  // 测试数据自动回滚了  数据库中 并不会添加进去
    public void saveTest() {
        ProductCategory productCategory = new ProductCategory("女生最爱", 2);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeIn() {
        List<Integer> list = Arrays.asList(1,2, 3, 4); //根据数据库ID 进行填写
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        log.info("size={}",result.size());
        Assert.assertNotEquals(0, result.size());  // 结果大于0  测试成功
    }


}