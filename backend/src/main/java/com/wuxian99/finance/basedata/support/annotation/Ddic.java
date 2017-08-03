package com.wuxian99.finance.basedata.support.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface Ddic {
	/**
     * 字典值
     */
    String name();
    /**
     * 映射字段名称(默认当前字段+Name)
     */
    String mapTo() default "";
}
