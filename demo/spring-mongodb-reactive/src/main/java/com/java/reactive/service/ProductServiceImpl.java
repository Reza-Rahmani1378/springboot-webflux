package com.java.reactive.service;

import com.java.reactive.dal.model.Product;
import com.java.reactive.dal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository repository;
    @Override
    public Flux<Product> getProducts() {
        return repository.findAll();
    }

    @Override
    public Mono<Product> saveProduct(Product product) {
        return repository.insert(product);
    }
}

