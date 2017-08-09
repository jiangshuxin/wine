package com.wuxian99.finance.basedata.web.dto;

/**
 * Created by za-jiangshuxin on 2017/8/9.
 */
public class ChangeOrderStatusDto {
    private Long orderId;
    private Long status;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
