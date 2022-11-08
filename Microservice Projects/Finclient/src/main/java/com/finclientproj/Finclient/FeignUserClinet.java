package com.finclientproj.Finclient;

import Model.CovidData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(url="https://coronavirus-19-api.herokuapp.com/",name="FEIGN")
public interface FeignUserClinet {

    @GetMapping("/countries")
    public List<CovidData> getCovidData();
}
