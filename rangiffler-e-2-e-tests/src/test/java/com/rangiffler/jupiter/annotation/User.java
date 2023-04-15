package com.rangiffler.jupiter.annotation;

import com.rangiffler.jupiter.extension.CreateUserExtension;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
public @interface User {

    CreateUserExtension.Selector selector() default CreateUserExtension.Selector.NESTED;
}
