package com.bdtv.ms.ecom.auth.service;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class AuthApplication {

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
	return new RestTemplate();
    }

    public static void main(String[] args) {
	SpringApplication.run(AuthApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurerAdapter() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
			.allowedMethods("*").allowedHeaders("*")
			.allowCredentials(true)
			.exposedHeaders(HttpHeaders.SET_COOKIE).maxAge(3600L);
	    }
	};
    }

    @GetMapping("/user")
    public Principal user(Principal user) {
	return user;
    }
}
