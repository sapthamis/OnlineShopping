package com.microservice.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loadbalance")
public class LoadBalancerController {
	@Autowired
	private Environment env;
	
	@RequestMapping(path="/status/check")
	public String status() {
		return "Working on port "+env.getProperty("local.server.port");
	}

}
