package com.hy.dusk.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: ZhaoZhan
 * @date: 2022-12-23 15:53
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GenLog {

    GenLogEnum value() default GenLogEnum.COMMON;

}
