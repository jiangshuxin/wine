package com.wuxian99.finance.basedata.web.dto;

public class QueryOrderListDto extends Pagination{
    private Long userId;
    private Long status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
