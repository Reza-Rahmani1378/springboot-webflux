package com.java.webflux.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;

@SpringBootTest
class SpringbootWebfluxDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testMono() {
		Mono<String> monoString = Mono.just("Java").log();
		monoString.subscribe(System.out::println);
	}



}
