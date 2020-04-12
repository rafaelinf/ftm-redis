package com.financial.transaction.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableCaching
public class FinancialTransactionRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialTransactionRedisApplication.class, args);
	}

}
