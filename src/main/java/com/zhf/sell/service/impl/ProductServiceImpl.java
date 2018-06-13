package com.zhf.sell.service.impl;

import com.zhf.sell.dataobject.ProductInfo;
import com.zhf.sell.dto.CartDTO;
import com.zhf.sell.enums.ProductStatusEnum;
import com.zhf.sell.enums.ResultEnum;
import com.zhf.sell.exception.ResponseBankException;
import com.zhf.sell.exception.SellException;
import com.zhf.sell.repository.ProductInfoRepository;
import com.zhf.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * 商品
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public ProductInfo findOne(String productId) {
        try{
            return repository.findById(productId).get();
        }catch (Exception e){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            //假设抛给银行异常 （用于修改状态码）
//            throw new ResponseBankException();
        }
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode()); // 查询上架商品
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {

        for (CartDTO cartDTO : cartDTOList) {
            try{
                ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
                Integer result = productInfo.getProductStock() + cartDTO.getProductQuantiy();
                productInfo.setProductStock(result);
                repository.save(productInfo);
            }catch (Exception e){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            //根据商品ID进行查询 判断库存是否够
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if (null == productInfo) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            //TODO 超卖？？？
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantiy();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }

    }

    @Override
    public ProductInfo onSale(String productId) {
        return updateSale(productId, ProductStatusEnum.UP);
    }

    @Override
    public ProductInfo offSale(String productId) {
        return updateSale(productId, ProductStatusEnum.DOWN);
    }

    //上下架
    private ProductInfo updateSale(String productId, ProductStatusEnum productStatusEnum) {
        ProductInfo productInfo = new ProductInfo();
        try {
            productInfo = repository.findById(productId).get();
            if (productInfo.getProductStatusEnum() == productStatusEnum) {
                throw new SellException(ResultEnum.PRODUCT_STATUS_ERROR);
            }
            //更新
            productInfo.setProductStatus(productStatusEnum.getCode());
            repository.save(productInfo);
        } catch (Exception e) {
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }
        return productInfo;
    }

}
