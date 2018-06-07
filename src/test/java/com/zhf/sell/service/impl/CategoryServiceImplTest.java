package com.zhf.sell.service.impl;

import com.zhf.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 类目
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceImplTest {

    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory productCategory= categoryService.findOne(1);
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());  // 判断ID 是否为1  Y:测试成功 N：测试失败
    }

    @Test
    public void findAll() {
        List<ProductCategory> result =categoryService.findAll();
        Assert.assertNotEquals(0, result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result =categoryService.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0, result.size());
    }


    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory("男生专享", 10);
        ProductCategory result=categoryService.save(productCategory);
        Assert.assertNotEquals(null,result);
    }
}