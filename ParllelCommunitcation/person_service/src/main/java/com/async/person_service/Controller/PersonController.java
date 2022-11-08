package com.async.person_service.Controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
public class PersonController {

    @GetMapping("/name/{id}")
    public String getName(@PathVariable("id") String id) throws InterruptedException{
        Thread.sleep(4000);
        System.out.print(id);
        if(id.equals("Raj"))
            return "Dev";
        throw new RuntimeException();
    }
}
