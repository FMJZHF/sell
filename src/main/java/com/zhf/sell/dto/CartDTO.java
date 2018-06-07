package com.zhf.sell.dto;

import lombok.Data;

/**
 * 购物车
 */
@Data
public class CartDTO {
    /** 商品ID */
    private String productId;

    /** 商品数量 */
    private Integer productQuantiy;

    public CartDTO() {
    }

    public CartDTO(String productId, Integer productQuantiy) {
        this.productId = productId;
        this.productQuantiy = productQuantiy;
    }
}
