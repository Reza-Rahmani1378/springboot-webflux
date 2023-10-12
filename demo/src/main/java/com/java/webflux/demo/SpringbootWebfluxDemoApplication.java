package com.java.webflux.demo;

import com.java.webflux.crud.api.controller.EmployeeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.java"})
/*@ComponentScan(basePackages = {"com.java"})*/
@EnableReactiveMongoRepositories(basePackages = {"com.java.webflux.crud.dal.repository"})
public class SpringbootWebfluxDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWebfluxDemoApplication.class, args);
	}

}
