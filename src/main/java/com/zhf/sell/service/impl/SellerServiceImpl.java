package com.zhf.sell.service.impl;

import com.zhf.sell.dataobject.SellerInfo;
import com.zhf.sell.repository.SellerInfoRepository;
import com.zhf.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openId) {
        return repository.findByOpenid(openId);
    }
}
