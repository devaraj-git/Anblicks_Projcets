package com.user.microservice.userservice.Controller;

import com.user.microservice.userservice.Controller.Servie.UserServiceImp;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserContorller {

    private static final String USER_SERVICE = "userService";


    @Autowired
    private Environment env;

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/status/check")
    public String status(){
        return "Port Number"+env.getProperty("local.server.port");
    }

    @GetMapping("/wish")
    public String work(){
        String url="http://localhost:8086/accountmg/work";
        String result = restTemplate.getForObject(url,String.class);
        return "Dev " + result;
    }


    @GetMapping("/user")
    @CircuitBreaker(name=USER_SERVICE,fallbackMethod="orderFallBack")
    public Mono<String> createOrder(){
        return userServiceImp.Order();
    }






}
