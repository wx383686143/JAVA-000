package io.kimmking.dubbo.demo.api;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FreezeAmount {

    private Long freezeId;
    private BigDecimal freezeDollarAmount;
    private BigDecimal freezeRmbAmount;
}
