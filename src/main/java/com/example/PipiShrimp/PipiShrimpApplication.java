package com.example.PipiShrimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//exclude = { SecurityAutoConfiguration.class }:排除Spring Security預設配置
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class PipiShrimpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PipiShrimpApplication.class, args);
	}

}
