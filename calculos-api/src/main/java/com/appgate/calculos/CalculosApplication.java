package com.appgate.calculos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CalculosApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalculosApplication.class, args);
    }
}
