package com.rangiffler.jupiter.annotation;

import com.rangiffler.model.enums.Country;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface TravelPhotos {

    String description() default "";

    String imgPath() default "src/test/resources/photos/berlin.jpeg";

    Country country() default Country.RUSSIA;

    boolean handleAnnotation() default true;

    int count() default 0;
}
