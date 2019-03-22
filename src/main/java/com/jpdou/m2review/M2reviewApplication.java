package com.jpdou.m2review;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication
public class M2reviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(M2reviewApplication.class, args);
	}
}
