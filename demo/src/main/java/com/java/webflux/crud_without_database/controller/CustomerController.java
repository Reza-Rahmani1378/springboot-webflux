package com.java.webflux.crud_without_database.controller;

import com.java.webflux.crud_without_database.dto.Customer;
import com.java.webflux.crud_without_database.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping
    public List<Customer> getAllCustomers() {
        return service.loadAllCustomers();
    }

    @GetMapping(value = "/stream" , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersStream() {
        return service.loadAllCustomersStream();
    }
}
