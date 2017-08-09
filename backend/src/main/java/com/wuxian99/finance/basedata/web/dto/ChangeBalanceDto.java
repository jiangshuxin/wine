package com.wuxian99.finance.basedata.web.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by za-jiangshuxin on 2017/8/9.
 */
public class ChangeBalanceDto {
    @NotNull
    private Long userId;

    @NotNull
    private BigDecimal amount;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
