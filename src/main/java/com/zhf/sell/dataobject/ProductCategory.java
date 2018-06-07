package com.zhf.sell.dataobject;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.zhf.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 类目
 */
@Entity
@DynamicUpdate  // 动态更新
@Data  // 自动生成额get，set，tostring方法  //  @Get  只生成get方法
public class ProductCategory {
    //类目ID
    @Id //主键
//    @GeneratedValue  // 自增类型
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    //类目名字
    private String categoryName;

    //类目编号
    private Integer categoryType;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;


    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}

