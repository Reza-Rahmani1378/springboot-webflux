package com.java.webflux.demo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    void testMono() {
        Mono<?> monoString = Mono.just("Java Reactive Programing")
                .then(Mono.error(new RuntimeException("Runtim Exception")))
                .log();
        monoString.subscribe(System.out::println,(e) -> System.out.println(e.getMessage()));
    }

    @Test
    void testFlux() {
        Mono<String> monoString = Mono.just("Java");
        Flux<String> fluxString = Flux
                .just("Spring", "Spring boot", "Netty", "Spring Webflux")
                .concatWithValues("AWS")
                .concatWith(monoString)
                .concatWith(Flux.error(new RuntimeException("Exception occurred in Flux")))
                .concatWithValues("Cloud")
                .log();
        fluxString.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }
}
