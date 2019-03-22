package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.EurekaClient;

@RestController
public class MyController {
	@Autowired
	private EurekaClient client;

	@Autowired
	private RestTemplateBuilder builder;

	@GetMapping("/")
	public String getHello() {
		return this.builder.build().exchange(client.getNextServerFromEureka("SERVICE", false).getHomePageUrl(), 
				HttpMethod.GET, null, String.class).getBody();
	}
}
