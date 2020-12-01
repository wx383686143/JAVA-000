package org.geekbang.ecommerce.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
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
}
