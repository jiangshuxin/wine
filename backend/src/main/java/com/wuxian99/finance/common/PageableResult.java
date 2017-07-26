package com.wuxian99.finance.common;

import java.util.List;
import java.util.Map;

/**
 * datatable识别的分页返回格式
 * Created by sxjiang on 2017/3/2.
 */
@SuppressWarnings("rawtypes")
public class PageableResult extends Result {
    private static final long serialVersionUID = -1L;

    private Integer recordsFiltered;
    private Integer recordsTotal;

    private List<String> options;
    private Map<String,Map<String,List>> files;

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

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public Map<String, Map<String, List>> getFiles() {
        return files;
    }

    public void setFiles(Map<String, Map<String, List>> files) {
        this.files = files;
    }
}
