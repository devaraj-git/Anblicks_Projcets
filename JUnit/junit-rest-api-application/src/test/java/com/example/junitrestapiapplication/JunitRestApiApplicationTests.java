package com.example.junitrestapiapplication;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
class JunitRestApiApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void demoTestMethod(){
        assertTrue(true);
    }

}
