package com.zhf.sell.controller;

import com.zhf.sell.dataobject.ProductCategory;
import com.zhf.sell.dataobject.ProductInfo;
import com.zhf.sell.service.CategoryService;
import com.zhf.sell.service.ProductService;
import com.zhf.sell.utils.ResultVoUtil;
import com.zhf.sell.vo.ProductInfoVo;
import com.zhf.sell.vo.ProductVo;
import com.zhf.sell.vo.ResultVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 卖家商品相关
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "product" , key="123")  // redis 进行缓存数据， 只执行一次
    public ResultVo list() {
        //1.查询所有上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目（一次性查询）
        /*
        List<Integer> categoryTypeList = new ArrayList<>();
        //传统方法
        for (ProductInfo productInfo:productInfoList) {
            categoryTypeList.add(productInfo.getCategoryType());
        }
        */
        //精简方法（java8 ， lambda）  lambda表达式
        List<Integer> categoryTypeList = productInfoList.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVo> productVoList = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVo productVo = new ProductVo();
            productVo.setCategoryName(productCategory.getCategoryName());
            productVo.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVoList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVo productInfoVo = new ProductInfoVo();
                    //对象拷贝
                    BeanUtils.copyProperties(productInfo, productInfoVo);
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setProductInfoVoList(productInfoVoList);
            productVoList.add(productVo);
        }


//        ResultVo resultVo = new ResultVo();
//        ProductVo productVo = new ProductVo();
//        ProductInfoVo productInfoVo = new ProductInfoVo();
//        List<P>

//        productVo.setCategoryName("");
//        productVo.setCategoryType();
//        productVo.setProductInfoVoList(Arrays.asList(productInfoVo));

//        resultVo.setCode(0);
//        resultVo.setMsg("成功");
//        resultVo.setData(Arrays.asList(productVo));
//        resultVo.setData(productVoList);

        return ResultVoUtil.success(productVoList);
    }

}
