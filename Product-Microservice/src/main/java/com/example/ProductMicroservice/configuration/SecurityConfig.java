package com.example.ProductMicroservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public DefaultSecurityFilterChain configure(HttpSecurity httpSecurity)throws Exception{
        httpSecurity
                .authorizeHttpRequests(authorize->authorize.anyRequest().authenticated())
                .oauth2ResourceServer((oauth2)->oauth2.jwt(Customizer.withDefaults()));
        return httpSecurity.build();
    }
}
