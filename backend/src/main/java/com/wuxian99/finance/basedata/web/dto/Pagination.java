package com.wuxian99.finance.basedata.web.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

/**
 * Created by sxjiang on 2017/7/20.
 */
public class Pagination {
    /**
     * 从1开始
     */
    private Integer pageNumber;
    private Integer pageSize;

    public static Integer DEFAULT_SIZE = 5;
    public static Integer DEFAULT_NUMBER = 0;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageRequest convert(Sort sort){
        Integer page = (pageNumber == null?DEFAULT_NUMBER:pageNumber-1);
        Integer size = (pageSize == null?DEFAULT_SIZE:pageSize);
        PageRequest pageRequest = new PageRequest(page, size, sort);
        return pageRequest;
    }

    public PageRequest convert(){
        Integer page = (pageNumber == null?DEFAULT_NUMBER:pageNumber-1);
        Integer size = (pageSize == null?DEFAULT_SIZE:pageSize);
        PageRequest pageRequest = new PageRequest(page, size);
        return pageRequest;
    }
}
