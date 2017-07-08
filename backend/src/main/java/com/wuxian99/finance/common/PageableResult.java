package com.wuxian99.finance.common;

/**
 * Created by sxjiang on 2017/3/2.
 */
@SuppressWarnings("rawtypes")
public class PageableResult extends Result {
    private static final long serialVersionUID = -1L;

    private Integer recordsFiltered;
    private Integer recordsTotal;

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }
}
