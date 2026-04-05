package com.example.micorservices.hotel_catelog_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class HotelCatalogServiceApplication {

	@Bean
	public RestTemplate restTemplate()  // this bean initialization could be in any @Component which should be in class path
	{
		return new RestTemplate();
	}

	@Bean
	public WebClient.Builder getWebClientBuilder()
	{
		return WebClient.builder();
	}

	public static void main(String[] args) {
		SpringApplication.run(HotelCatalogServiceApplication.class, args);
	}

}
