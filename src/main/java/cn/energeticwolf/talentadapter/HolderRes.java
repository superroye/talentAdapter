package cn.energeticwolf.talentadapter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Roye on 2016/12/12.
 */

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface HolderRes {
    int value() default 0;
}
