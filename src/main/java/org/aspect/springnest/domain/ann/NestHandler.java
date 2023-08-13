package org.aspect.springnest.domain.ann;

import org.aspect.springnest.domain.NestMain;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NestHandler {
    Class<? extends Exception> onException() default Exception.class;
    Class<? extends NestMain> handle();
}
