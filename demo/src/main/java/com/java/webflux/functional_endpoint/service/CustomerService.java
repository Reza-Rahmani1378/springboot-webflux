package com.java.webflux.functional_endpoint.service;

import com.java.webflux.functional_endpoint.dao.CustomerDao;
import com.java.webflux.functional_endpoint.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {


    private final CustomerDao dao;

    public List<Customer> loadAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getCustomers();
        long end = System.currentTimeMillis();

        System.out.println("Total Execution time is :" + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getCustomersStream();
        long end = System.currentTimeMillis();

        System.out.println("Total Execution time is :" + (end - start));
        return customers;
    }

}
