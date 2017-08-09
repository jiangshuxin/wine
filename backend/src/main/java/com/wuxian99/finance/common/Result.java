package com.wuxian99.finance.common;

import java.io.Serializable;

/**
 * Created by sxjiang on 2017/3/2.
 */
public class Result<T> implements Serializable{
    private static final long serialVersionUID = -1L;

    private Boolean isSuccess;
    private String errorMsg;
    private T data;
    private Long totalCount;

    public Boolean getSuccess() {
        return isSuccess;
    }

    public void setSuccess(Boolean success) {
        isSuccess = success;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public static Result<?> buildSuccess(){
        Result<?> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> buildSuccess(T data){
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> buildSuccess(T data, Long totalCount){
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        result.setTotalCount(totalCount);
        return result;
    }

    public static <T> Result<T> buildFail(String msg){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setErrorMsg(msg);
        return result;
    }

    public static <T> Result<T> buildFail(org.springframework.validation.FieldError fieldError){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setErrorMsg(String.format("%s.%s%s",fieldError.getObjectName(),fieldError.getField(),fieldError.getDefaultMessage()));
        return result;
    }
}
