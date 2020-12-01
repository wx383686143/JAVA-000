package org.geekbang.ecommerce.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderVO implements Serializable {
    private Long pk;
    private String orderNo;
    private BigDecimal count;
    private BigDecimal price;
    private Integer status;
    private Long goodsFK;
    private Long userFK;
    private Date createdTime;
    private Date updateTime;

    public Long getPk() {
        return pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getGoodsFK() {
        return goodsFK;
    }

    public void setGoodsFK(Long goodsFK) {
        this.goodsFK = goodsFK;
    }

    public Long getUserFK() {
        return userFK;
    }

    public void setUserFK(Long userFK) {
        this.userFK = userFK;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
