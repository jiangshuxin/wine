package com.wuxian99.finance.basedata.support.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 文件上传注解,用于业务表中表示的upload_file.id的属性,可以帮助填充path等参数
 * Created by sxjiang on 2017/7/29.
 */
@Target({FIELD})
@Retention(RUNTIME)
public @interface UploadRef {

    String ref() default "";

    String type() default "PATH";
}
