package com.java.webflux.crud_without_database.handler;

import com.java.webflux.crud_without_database.dao.CustomerDao;
import com.java.webflux.crud_without_database.dto.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerStreamHandler {

    private final CustomerDao dao;

    public Mono<ServerResponse> getCustomers(ServerRequest request) {
        Flux<Customer> customersStream = dao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }
}
