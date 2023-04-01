package com.rangiffler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.rangiffler")
public class RangifflerGatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(RangifflerGatewayApp.class, args);
  }
}
