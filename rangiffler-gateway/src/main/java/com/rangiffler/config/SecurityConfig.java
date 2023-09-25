package com.rangiffler.config;

import com.rangiffler.cors.CorsCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

  private static final String[] SWAGGER_ENDPOINTS = {
          "/swagger-ui/**",
          "/swagger-resources/**",
          "/authenticate",
          "/v3/api-docs",
          "/v3/api-docs/swagger-config/**",
          "/configuration/security",
          "/webjars/**",
          "/swagger-ui-index.html",
  };

  private final CorsCustomizer corsCustomizer;

  public SecurityConfig(CorsCustomizer corsCustomizer) {
    this.corsCustomizer = corsCustomizer;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    corsCustomizer.corsCustomizer(http);

    http.authorizeHttpRequests()
            .requestMatchers("/actuator/health").permitAll()
            .requestMatchers(SWAGGER_ENDPOINTS).permitAll()
            .anyRequest()
            .authenticated().and()
            .oauth2ResourceServer()
            .jwt();
    return http.build();
  }
}
