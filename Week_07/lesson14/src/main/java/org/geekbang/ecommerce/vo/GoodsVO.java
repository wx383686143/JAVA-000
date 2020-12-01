package org.geekbang.ecommerce.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsVO implements Serializable {
    private Long pk;
    private String goodsNo;
    private String goodsName;
    private String goodsType;
    private Integer quantity;
    private BigDecimal price;
    private Date createdTime;
    private Date updateTime;
}
