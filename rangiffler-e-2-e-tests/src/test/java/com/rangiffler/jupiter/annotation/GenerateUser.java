package com.rangiffler.jupiter.annotation;

import com.rangiffler.jupiter.extension.ClearCookiesAndSessionExtension;
import com.rangiffler.jupiter.extension.CreateUserExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@ExtendWith({CreateUserExtension.class, ClearCookiesAndSessionExtension.class})
public @interface GenerateUser {

    boolean handleAnnotation() default true;

    String username() default "";

    String password() default "";

    Friends friends() default @Friends(handleAnnotation = false);

    Travels travels() default @Travels(handleAnnotation = false);

    IncomeInvitations incomeInvitations() default @IncomeInvitations(handleAnnotation = false);

    OutcomeInvitations outcomeInvitations() default @OutcomeInvitations(handleAnnotation = false);

}
