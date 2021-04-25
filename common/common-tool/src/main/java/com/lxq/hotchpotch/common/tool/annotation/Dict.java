package com.lxq.hotchpotch.common.tool.annotation;

import java.lang.annotation.*;

/**
 * @author luxinqiang
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Dict {

    String code();

    String labelField() default "";
}
