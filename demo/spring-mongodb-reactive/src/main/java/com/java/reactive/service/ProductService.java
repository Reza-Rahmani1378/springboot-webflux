package com.java.reactive.service;

import com.java.reactive.dal.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> getProducts();

    Mono<Product> saveProduct(Product product);
}
