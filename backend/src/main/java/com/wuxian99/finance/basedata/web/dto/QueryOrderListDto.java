package com.wuxian99.finance.basedata.web.dto;

import com.google.gson.Gson;

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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
