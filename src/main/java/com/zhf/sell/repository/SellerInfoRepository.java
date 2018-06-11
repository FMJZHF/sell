package com.zhf.sell.repository;

import com.zhf.sell.dataobject.OrderDetail;
import com.zhf.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 卖家
 */
public interface SellerInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openId);
}
