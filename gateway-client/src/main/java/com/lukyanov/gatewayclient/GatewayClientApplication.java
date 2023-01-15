package com.lukyanov.gatewayclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GatewayClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayClientApplication.class, args);
    }
}
