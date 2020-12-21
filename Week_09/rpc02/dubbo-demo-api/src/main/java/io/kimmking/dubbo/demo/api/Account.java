package io.kimmking.dubbo.demo.api;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Account implements Serializable {
    private Long userId;
    private BigDecimal dollarAmount;
    private BigDecimal rmbAmount;
    private BigDecimal freezeAmount;
}
