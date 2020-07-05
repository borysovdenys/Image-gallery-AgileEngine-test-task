package com.borysov.agileengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URISyntaxException;

@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
public class AgileengineApplication {
	public static void main(String[] args) throws URISyntaxException {
		SpringApplication.run(AgileengineApplication.class, args);
	}

}
