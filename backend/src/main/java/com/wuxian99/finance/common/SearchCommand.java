package com.wuxian99.finance.common;

/**
 * Created by sxjiang on 2017/3/6.
 */
public class SearchCommand {
    private String value;
    private Boolean regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getRegex() {
        return regex;
    }

    public void setRegex(Boolean regex) {
        this.regex = regex;
    }
}
