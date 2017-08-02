package com.wuxian99.finance.basedata.web.dto;

import com.google.gson.Gson;

public class QueryUserAddressListDto extends Pagination{
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
