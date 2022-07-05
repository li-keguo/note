package com.example.mybatis.generator.po;

import java.util.Date;
import javax.persistence.*;

/**
 * XmConsumer
 */
@Table(name = "xm_consumer")
public class XmConsumer {
    /**
     * 消费者id
     */
    @Id
    @Column(name = "consumer_id")
    private Long consumerId;

    /**
     * 登录名称
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    @Column(name = "login_password")
    private String loginPassword;

    /**
     * vip等级
     */
    @Column(name = "vip_level")
    private Integer vipLevel;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 默认用户地址
     */
    @Column(name = "default_shopping_address")
    private byte[] defaultShoppingAddress;

    /**
     * 获取消费者id
     *
     * @return consumer_id - 消费者id
     */
    public Long getConsumerId() {
        return consumerId;
    }

    /**
     * 设置消费者id
     *
     * @param consumerId 消费者id
     */
    public void setConsumerId(Long consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * 获取登录名称
     *
     * @return login_name - 登录名称
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * 设置登录名称
     *
     * @param loginName 登录名称
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    /**
     * 获取昵称
     *
     * @return nickname - 昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置昵称
     *
     * @param nickname 昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * 获取密码
     *
     * @return login_password - 密码
     */
    public String getLoginPassword() {
        return loginPassword;
    }

    /**
     * 设置密码
     *
     * @param loginPassword 密码
     */
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword == null ? null : loginPassword.trim();
    }

    /**
     * 获取vip等级
     *
     * @return vip_level - vip等级
     */
    public Integer getVipLevel() {
        return vipLevel;
    }

    /**
     * 设置vip等级
     *
     * @param vipLevel vip等级
     */
    public void setVipLevel(Integer vipLevel) {
        this.vipLevel = vipLevel;
    }

    /**
     * 获取电话号码
     *
     * @return phone - 电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话号码
     *
     * @param phone 电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取默认用户地址
     *
     * @return default_shopping_address - 默认用户地址
     */
    public byte[] getDefaultShoppingAddress() {
        return defaultShoppingAddress;
    }

    /**
     * 设置默认用户地址
     *
     * @param defaultShoppingAddress 默认用户地址
     */
    public void setDefaultShoppingAddress(byte[] defaultShoppingAddress) {
        this.defaultShoppingAddress = defaultShoppingAddress;
    }
}
