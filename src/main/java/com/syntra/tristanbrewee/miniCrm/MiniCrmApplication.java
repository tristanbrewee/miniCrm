package com.syntra.tristanbrewee.miniCrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={
		"com.syntra.tristanbrewee.miniCrm"})
@EnableJpaRepositories
public class MiniCrmApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniCrmApplication.class, args);
	}

}
