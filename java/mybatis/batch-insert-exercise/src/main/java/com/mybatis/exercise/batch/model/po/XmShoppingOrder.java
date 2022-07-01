package com.mybatis.exercise.batch.model.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Table;

/**
 * XmShoppingOrder
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "xm_shopping_order")
public class XmShoppingOrder {
    /**
     * 订单id
     */
    @Column(name = "order_id")
    private String orderId;
    /**
     * 用户id
     */
    @Column(name = "consumer_id")
    private Integer consumerId;
    /**
     * 送货地址id
     */
    @Column(name = "shipping_address_id")
    private Integer shippingAddressId;
    /**
     * 消费者电话号码
     */
    @Column(name = "consumer_phone")
    private String consumerPhone;
    /**
     * 送货地址快照
     */
    @Column(name = "address_snapshot")
    private String addressSnapshot;
    /**
     * 订单状态
     */
    @Column(name = "order_status")
    @Builder.Default
    private String orderStatus = "未支付";
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @Builder.Default
    private Date createTime = new Date();
    /**
     * 修改时间
     */
    @Column(name = "update_time")
    @Builder.Default
    private Date updateTime = new Date();

}
