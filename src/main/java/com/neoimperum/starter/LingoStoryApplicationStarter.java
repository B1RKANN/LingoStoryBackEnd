package com.neoimperum.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.neoimperum"})
@EntityScan(basePackages = {"com.neoimperum"})
@EnableJpaRepositories(basePackages = {"com.neoimperum"})
@SpringBootApplication
public class LingoStoryApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(LingoStoryApplicationStarter.class, args);
	}

}
