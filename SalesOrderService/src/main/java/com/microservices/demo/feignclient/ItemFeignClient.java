package com.microservices.demo.feignclient;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.demo.dto.ItemServiceDTO;

@FeignClient(value = "API-GATEWAY")
public interface ItemFeignClient {
	@GetMapping(value="/items/{itemName}",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ItemServiceDTO get(@PathVariable String itemName);

}
