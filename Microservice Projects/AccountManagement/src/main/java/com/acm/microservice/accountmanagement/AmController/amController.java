package com.acm.microservice.accountmanagement.AmController;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/accountmg")
public class amController {

    @GetMapping("/status/accountcheck")
    public String checkName(){

        return "AccountManagerContorller";
    }

    @GetMapping("/work")
    public String work(){
        return "Working";
    }
}
