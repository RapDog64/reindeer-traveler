package com.rangiffler.jupiter.annotation;

import com.rangiffler.jupiter.extension.BeforeSuiteExtension;
import com.rangiffler.jupiter.extension.BrowserExtension;
import com.rangiffler.jupiter.extension.ClearCookiesAndSessionExtension;
import io.qameta.allure.junit5.AllureJunit5;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@ExtendWith({BeforeSuiteExtension.class, BrowserExtension.class, ClearCookiesAndSessionExtension.class, AllureJunit5.class})
public @interface WebTest {
}
