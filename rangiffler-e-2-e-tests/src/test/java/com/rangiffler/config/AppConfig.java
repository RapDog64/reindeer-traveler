package com.rangiffler.config;

import org.aeonbits.owner.ConfigFactory;

public class AppConfig {

    public static final ApplicationConfig config = ConfigFactory.create(ApplicationConfig.class, System.getProperties());

}
