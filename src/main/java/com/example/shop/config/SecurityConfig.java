package com.example.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf().disable();
        httpSecurity.authorizeHttpRequests(authorization -> {
            authorization
                    .requestMatchers("/products/update")
                    .hasRole("admin");
            authorization
                    .requestMatchers("/check_user_order")
                    .hasRole("admin");
            authorization
                    .requestMatchers("/check_user_review")
                    .hasRole("admin");
            authorization
                    .requestMatchers("/security_controller/current_user")
                    .authenticated();
            authorization
                    .requestMatchers("/products/cart1")
                    .authenticated();
            authorization
                    .requestMatchers("/products/order")
                    .authenticated();
            authorization
                    .requestMatchers(
                            new AntPathRequestMatcher("/products/{productId}")
                    );
            authorization
                    .anyRequest()
                    .permitAll();

        });
        httpSecurity.formLogin();
        return httpSecurity.build();
    }
}
