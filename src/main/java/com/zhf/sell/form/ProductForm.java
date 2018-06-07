package com.zhf.sell.form;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhf.sell.enums.ProductStatusEnum;
import com.zhf.sell.utils.EnumUtil;
import com.zhf.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 表单提交对象
 */
@Data
public class ProductForm {


    private String productId;
    //名字
    private String productName;
    //单价
    private BigDecimal productPrice;
    //库存
    private Integer productStock;
    //描述
    private String productDescription;
    //小图
    private String productIcon;
    //类目编号
    private Integer categoryType;


}
