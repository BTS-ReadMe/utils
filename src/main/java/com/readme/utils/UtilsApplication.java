package com.readme.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableEurekaClient
@EnableJpaAuditing
public class UtilsApplication {

	public static void main(String[] args) {
		setDefaultTimeZone();
		SpringApplication.run(UtilsApplication.class, args);
	}

	private static void setDefaultTimeZone() {
		java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Asia/Seoul"));
	}
}
