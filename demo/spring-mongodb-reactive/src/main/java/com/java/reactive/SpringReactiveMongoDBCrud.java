package com.java.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = {"com.java"})
@EnableReactiveMongoRepositories(basePackages = {"com.java.reactive.dal.repository"})
public class SpringReactiveMongoDBCrud {

    public static void main(String[] args) {
        SpringApplication.run(SpringReactiveMongoDBCrud.class, args);
    }
}
