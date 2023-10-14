package com.java.webflux.crud_without_database.router;

import com.java.webflux.crud_without_database.handler.CustomerHandler;
import com.java.webflux.crud_without_database.handler.CustomerStreamHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final CustomerHandler handler;

    private final CustomerStreamHandler streamHandler;


    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers",handler::loadCustomers)
                .GET("/router/customers/stream", streamHandler::getCustomers)
                .GET("/router/customer/{input}", handler::findCustomer)
                .POST("/router/customer/save", handler::saveCustomer)
                .build();
    }
}
