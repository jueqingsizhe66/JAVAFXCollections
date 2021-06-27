package com.anno.anno;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 只有被check的方法才进行检查
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Check {

}
