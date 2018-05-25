package com.bdtv.ms.ecom.cart.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * @author dangao on 5/10/2018.
 * @version 1.0
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableFeignClients
public class CartApplication
{
	public static void main(String[] args)
	{
		SpringApplication.run(CartApplication.class, args);
	}
}
