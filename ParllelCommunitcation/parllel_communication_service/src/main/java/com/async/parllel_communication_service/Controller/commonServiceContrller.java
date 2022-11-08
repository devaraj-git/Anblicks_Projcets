package com.async.parllel_communication_service.Controller;

import com.async.parllel_communication_service.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user-details")
public class commonServiceContrller {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${name.url}")
    private String nameUrl;

    @Value("${salary.url}")
    private String salaryUrl;

    @GetMapping("/{id}")
    public Person getUserDtails(@PathVariable("id") String  id){
        StopWatch s = new StopWatch();
        s.start();
        Person p = new Person();
     /*
    These are 2 services communicated but it will take much time to get the response for perosn 4s and salary 4s total 8s
     ResponseEntity<String>  name= restTemplate.exchange("http://localhost:8081/person/name/"+id, HttpMethod.GET,null,String.class);
       ResponseEntity<String> salary= restTemplate.exchange("http://localhost:8082/salary/"+id, HttpMethod.GET,null,String.class);

       p.setName(name.getBody());
       p.setSalary(salary.getBody());

      */

        // Now by using completable feature we are reducing the time to get parllel communication

        CompletableFuture.allOf(
                CompletableFuture.supplyAsync(()-> restTemplate.exchange(nameUrl+id,
                        HttpMethod.GET,null,String.class)).thenAccept(x -> p.setName(x.getBody())),
                CompletableFuture.supplyAsync(()-> restTemplate.exchange(salaryUrl+id,
                        HttpMethod.GET,null,String.class)).thenAccept(x->p.setSalary(x.getBody()))).join();
       s.stop();
       System.out.println("================ Total Time =========== "+ s.getTotalTimeMillis());
       return  p;
    }
}
