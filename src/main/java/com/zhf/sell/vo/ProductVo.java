package com.zhf.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 */
@Data
public class ProductVo {

    @JsonProperty("name") // 规定返回前端的属性名
    private String categoryName;

    @JsonProperty("type") // 规定返回前端的属性名
    private Integer categoryType;

    @JsonProperty("foods") // 规定返回前端的属性名
    private List<ProductInfoVo> productInfoVoList;
}
