package com.zhf.sell.dataobject;

import com.zhf.sell.enums.OrderStatusEnum;
import com.zhf.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Entity
@Data
@DynamicUpdate  //自动更新
public class OrderMaster {

    /** id */
    @Id
    private String orderId;
    /** 买家名字 */
    private String buyerName;
    /** 买家电话 */
    private String buyerPhone;
    /** 买家地址 */
    private String buyerAddress;
    /** 买家微信openid */
    private String buyerOpenid;
    /** 订单总金额*/
    private BigDecimal orderAmount;
    /** 订单状态 ，默认为0新下单*/
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    /** 支付状态，默认为0 未支付 */
    private Integer payStatus=PayStatusEnum.WAIT.getCode();
    /** 创建时间 */
    private Date createTime;
    /** 更新时间 */
    private Date updateTime;

}
