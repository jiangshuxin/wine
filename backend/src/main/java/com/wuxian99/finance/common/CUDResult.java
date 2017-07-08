package com.wuxian99.finance.common;

import java.util.List;

/**
 * Created by sxjiang on 2017/3/7.
 */
public class CUDResult<T> extends Result<T> {

	private static final long serialVersionUID = 2962789001527110575L;
	
	private List<FieldError> fieldErrors;

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldError> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
