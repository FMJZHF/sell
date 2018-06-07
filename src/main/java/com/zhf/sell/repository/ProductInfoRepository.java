package com.zhf.sell.repository;

import com.zhf.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 商品
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    //根据商品状态查询商品
    List<ProductInfo> findByProductStatus(Integer productStatus);
}
