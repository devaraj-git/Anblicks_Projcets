package com.Microservice.Organization.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrgRestController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getOrgDetails/{orgName}",method = RequestMethod.GET)
    public String getEmployees(@PathVariable String orgName){
        System.out.println("Getting Org Details "+ orgName);

        String  response = restTemplate.exchange("http://EmployeeService/getEmployeeDetailsForOrgnization/{orgname}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {},orgName).getBody();

        System.out.println("Recived Response"+response);

        return "Org Name - "+ orgName+"  \n Employee Details" + response;
    }



}
