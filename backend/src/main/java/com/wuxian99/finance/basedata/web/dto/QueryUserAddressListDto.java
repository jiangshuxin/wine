package com.wuxian99.finance.basedata.web.dto;

public class QueryUserAddressListDto extends Pagination{
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
