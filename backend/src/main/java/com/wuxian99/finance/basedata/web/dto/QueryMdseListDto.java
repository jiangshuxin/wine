package com.wuxian99.finance.basedata.web.dto;

public class QueryMdseListDto extends Pagination{
    private String merchantId;
    private String catagory;
    private String year;
    private String price;
    private String mdseIds;

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMdseIds() {
        return mdseIds;
    }

    public void setMdseIds(String mdseIds) {
        this.mdseIds = mdseIds;
    }
}
