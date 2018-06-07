package com.zhf.sell.repository;

import com.zhf.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *买家查询订单
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster ,String> {
    /** 按照买家openid查询， 并且进行分页查询*/
    Page<OrderMaster> findByBuyerOpenid(String buyOpenid, Pageable pageable);

}
