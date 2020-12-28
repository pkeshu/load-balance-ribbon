package com.keshar.ribbonexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RibbonClient(name = "service", configuration = ServiceConfiguration.class)
@RestController
@RequestMapping("/ribbon")
public class RibbonExampleApplication {
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String runLoadBalancer() {
        String fromService = restTemplate.getForObject("http://service/service/message", String.class);
        return fromService;
    }

    public static void main(String[] args) {
        SpringApplication.run(RibbonExampleApplication.class, args);
    }


}
