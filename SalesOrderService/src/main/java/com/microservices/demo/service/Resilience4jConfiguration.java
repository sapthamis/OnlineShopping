//package com.microservices.demo.service;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.microservices.demo.dto.ItemServiceDTO;
//import com.microservices.demo.feignclient.ItemFeignClient;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import jdk.internal.org.jline.utils.Log;
//
//@Service
//public class Resilience4jConfiguration {
//Logger logger = LoggerFactory.getLogger(Resilience4jConfiguration.class);
//	
//	long count = 1;
//	
//	@Autowired
//	ItemFeignClient itemFeignClient;
//	
//	@CircuitBreaker(name = "itemService",
//			fallbackMethod = "fallbackGet")
//	public ItemServiceDTO get(String itemName) {
//		logger.info("count = " + count);
//		count++;
//		ItemServiceDTO item = 
//				itemFeignClient.get(itemName);;
//		
//		return item;
//	}
//	
//	public ItemServiceDTO fallbackGet(String itemName,Throwable th) {
//		logger.error("Error = " + th);
//		
//		return null;
//	}
//	
//}
//	
//	
//	
//	
//	
//	
//
