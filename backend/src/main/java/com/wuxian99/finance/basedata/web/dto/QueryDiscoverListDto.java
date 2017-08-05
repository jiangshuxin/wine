package com.wuxian99.finance.basedata.web.dto;

import com.google.gson.Gson;

public class QueryDiscoverListDto extends Pagination{

    private String merchantId;

    private Long type;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
