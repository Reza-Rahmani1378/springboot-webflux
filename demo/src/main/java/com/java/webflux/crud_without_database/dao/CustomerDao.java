package com.java.webflux.crud_without_database.dao;

import com.java.webflux.crud_without_database.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class CustomerDao {


    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerDao::sleepExecution)
                .peek(i -> System.out.println("Processing Count :" + i))
                .mapToObj(i -> new Customer(i, "Customer" + i))
                .collect(Collectors.toList());
    }

    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("Processing Count" + i))
                .map(i -> new Customer(i , "Customer" + i));
    }

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000 + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
