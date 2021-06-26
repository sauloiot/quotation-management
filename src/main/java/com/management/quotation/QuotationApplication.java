package com.management.quotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class QuotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationApplication.class, args);
	}

}
