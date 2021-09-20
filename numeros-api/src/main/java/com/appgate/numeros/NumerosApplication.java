package com.appgate.numeros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class NumerosApplication {

    public static void main(String[] args) {
        SpringApplication.run(NumerosApplication.class, args);
    }
}
