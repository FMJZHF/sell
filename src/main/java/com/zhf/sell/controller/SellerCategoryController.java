package com.zhf.sell.controller;

import com.zhf.sell.dataobject.ProductCategory;
import com.zhf.sell.form.CategoryForm;
import com.zhf.sell.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 卖家类目
 */
@Controller
@RequestMapping("/seller/category")
@Slf4j
public class SellerCategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ModelAndView list(Map<String, Object> map) {
        List<ProductCategory> categoryList = categoryService.findAll();
        map.put("categoryList",  categoryList);
        return new ModelAndView("category/list", map);
    }

    /**
     * 展示
     * @param categoryId
     * @param map
     * @return
     */
    @GetMapping("/index")
    public ModelAndView list(@RequestParam(value = "categoryId",required = false) Integer categoryId,
                             Map<String, Object> map) {
        if(categoryId != null){ //说明有类目
            ProductCategory category=  categoryService.findOne(categoryId);
            map.put("category",  category);
        }
        return new ModelAndView("category/index", map);
    }

    /**
     * 保存/更新类目
     */
    @PostMapping("/save")
    public ModelAndView save(@Valid CategoryForm categoryForm,
                             BindingResult bindingResult,
                             Map<String, Object> map) {
        if(bindingResult.hasErrors()){
            map.put("msg", bindingResult.getFieldError().getDefaultMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }

        ProductCategory productCategory = new ProductCategory();
        try{
            log.info(">>>>>>>>>>>>getCategoryId={}",categoryForm.getCategoryId());
            log.info(">>>>>>>>>>>>isT={}",categoryForm.getCategoryId()!=null);

            //如果productId为空，说明是新增
            if(categoryForm.getCategoryId()!=null){
                productCategory=categoryService.findOne(categoryForm.getCategoryId());
            }
            BeanUtils.copyProperties(categoryForm , productCategory);
            categoryService.save(productCategory);
        }catch (Exception e){
            map.put("msg", e.getMessage());
            map.put("url", "/sell/seller/category/index");
            return new ModelAndView("common/error", map);
        }
        map.put("url", "/sell/seller/category/list");
        return new ModelAndView("common/success", map);
    }
}
