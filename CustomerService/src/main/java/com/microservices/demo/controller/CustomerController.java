package com.microservices.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.microservices.demo.dto.CustomerDTO;
import com.microservices.demo.service.CustomerService;

@RequestMapping("/customers")
@RestControllerAdvice
@RestController
public class CustomerController {
	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public ResponseEntity<List<CustomerDTO>> all() {
		List<CustomerDTO> customerDto = customerService.all();
		return new ResponseEntity<List<CustomerDTO>>(customerDto, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public CustomerDTO get(@PathVariable long id) {
		return customerService.get(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	public CustomerDTO put(@PathVariable long id, @RequestBody CustomerDTO customerDTO) {
		CustomerDTO customerDto = customerService.update(customerDTO, id);
		return customerDto;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		customerService.delete(id);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDTO add(@Valid @RequestBody CustomerDTO customerDTO) {
		return customerService.save(customerDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		LOG.error("There was an error: ", ex);
		// Add conditional logic to show different status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
