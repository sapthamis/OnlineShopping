package com.microservices.demo.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.demo.dto.CustomerDTO;
import com.microservices.demo.dto.ItemServiceDTO;

@FeignClient(value = "API-GATEWAY")
public interface ItemFeignClient {
	@GetMapping(value = "/items/{itemName}", consumes = MediaType.APPLICATION_JSON_VALUE)
	// Boolean get(@PathVariable String itemName);
	public ItemServiceDTO get(@PathVariable String itemName);

//	@GetMapping(value = "/customers/{id}")
//	public CustomerDTO get(@PathVariable long id);

}
