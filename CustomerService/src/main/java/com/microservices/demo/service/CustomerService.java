package com.microservices.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.demo.dto.CustomerDTO;
import com.microservices.demo.entity.Customer;
import com.microservices.demo.repository.CustomerRepository;

@Service
public class CustomerService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerRepository customerRepository;

	public List<CustomerDTO> all() {
		return customerRepository.findAll().stream().map(c -> modelMapper.map(c, CustomerDTO.class))
				.collect(Collectors.toList());
	}

	public CustomerDTO save(CustomerDTO customerDTO) {
		Customer customer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));
		CustomerDTO result = modelMapper.map(customer, CustomerDTO.class);
		return result;
	}

	public CustomerDTO update(CustomerDTO customerDTO, Long id) {
		Customer customer = customerRepository.getById(id);
		customer.setEmail(customerDTO.getEmail());
		customer.setFirstName(customerDTO.getFirstName());
		customer.setLastName(customerDTO.getLastName());
		Customer updatedCustomer = customerRepository.save(customer);
		return modelMapper.map(updatedCustomer, CustomerDTO.class);
	}

	public CustomerDTO get(long customerId) {
		Optional<Customer> customerResult = customerRepository.findById(customerId);
		if (!customerResult.isPresent()) {
			return null;
		}
		return modelMapper.map(customerResult.get(), CustomerDTO.class);
	}

	public void delete(long customerId) {
		customerRepository.deleteById(customerId);
	}
}
