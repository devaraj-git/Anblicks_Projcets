package com.user.microservice.userservice.Controller.Servie;

import com.user.microservice.userservice.Controller.UserContorller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImp {
    private static final Logger Log= LoggerFactory.getLogger(UserContorller.class);

    private final WebClient webClient;
    private final ReactiveCircuitBreaker readingListCircuitBreaker;

    public UserServiceImp(ReactiveCircuitBreakerFactory circuitBreakerFactory) {
        this.webClient = WebClient.builder().baseUrl("http://localhost:8086/accountmg/work").build();
        this.readingListCircuitBreaker = circuitBreakerFactory.create("recommended");
    }


    public Mono<String> Order() {
        return readingListCircuitBreaker.run(webClient.get().uri("/recommended").retrieve().bodyToMono(String.class),
                throwable -> {
            Log.warn("Error Making the request to this service", throwable);
            return Mono.just("Cloud Native Java");
                });
    }
}
