package com.jpdou.m2review.bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
