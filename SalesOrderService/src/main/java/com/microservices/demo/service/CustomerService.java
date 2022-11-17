package com.microservices.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.demo.dto.CustomerDTO;
import com.microservices.demo.entity.Customer;
import com.microservices.demo.messagelistener.CustomerMessageListener;

import com.microservices.demo.repository.CustomerSORepository;

@Service
public class CustomerService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerSORepository customerSORepository;
	

	public CustomerDTO save(CustomerMessageListener.Customer customer) {
		System.out.println("cust "+customer);
		Customer customer2 = customerSORepository.save(modelMapper.map(customer, Customer.class));
		System.out.println("cutomer "+customer2);
		return modelMapper.map(customer2, CustomerDTO.class);
		
		
	}
}
