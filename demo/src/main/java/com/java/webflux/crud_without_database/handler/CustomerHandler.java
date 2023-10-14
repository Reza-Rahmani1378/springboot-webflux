package com.java.webflux.crud_without_database.handler;

import com.java.webflux.crud_without_database.dao.CustomerDao;
import com.java.webflux.crud_without_database.dto.Customer;
import com.mongodb.internal.connection.Server;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CustomerHandler {

    private final CustomerDao dao;


    public Mono<ServerResponse> loadCustomers(ServerRequest request) {
        long start = System.currentTimeMillis();
        Flux<Customer> customerList = dao.getCustomerList();
        long end = System.currentTimeMillis();
        System.out.println("Total Execution time is :" + (end - start));
        return ServerResponse.ok().body(customerList, Customer.class );
    }

    public Mono<ServerResponse> findCustomer(ServerRequest request) {
        int customerId = Integer.parseInt(request.pathVariable("input"));
        Mono<Customer> customerMono = dao.getCustomerList().filter(c -> c.getId() == customerId).next();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customerMono, Customer.class);
    }

    public Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<Customer> customerMono = request.bodyToMono(Customer.class);
        Mono<String> saveResponse = customerMono.map(dto -> dto.getId() + ":" + dto.getName());
        return ServerResponse.ok().body(saveResponse , String.class);
    }


}
