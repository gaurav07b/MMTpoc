package com.mmt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@AutoConfigurationPackage
@EnableJpaRepositories
@ComponentScan("com.mmt.controller")
@ComponentScan("con.mmt.service")
@EntityScan("com.mmt.dao.entity")
@ComponentScan("com.mmt.config")
public class MakeMyTripApIsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MakeMyTripApIsApplication.class, args);
	}
}
