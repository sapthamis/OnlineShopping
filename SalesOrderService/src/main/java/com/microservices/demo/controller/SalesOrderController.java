package com.microservices.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.microservices.demo.dto.SalesOrderDTO;
import com.microservices.demo.service.SalesOrderService;

@RequestMapping("/orders")
@RestControllerAdvice
@RestController
public class SalesOrderController {
	private static final Logger LOG = LoggerFactory.getLogger(SalesOrderController.class);

	@Autowired
	private SalesOrderService salesOrderService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public List<SalesOrderDTO> all() {
		return salesOrderService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	public SalesOrderDTO get(@PathVariable long id) {
		return salesOrderService.get(id);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	public SalesOrderDTO put(@PathVariable long id, @RequestBody SalesOrderDTO salesOrderDTO) {
		SalesOrderDTO salesOrderDto = salesOrderService.update(salesOrderDTO, id);
		return salesOrderDto;

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		salesOrderService.delete(id);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public SalesOrderDTO add(@RequestBody SalesOrderDTO salesOrderDTO) {
		
		return salesOrderService.save(salesOrderDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		LOG.error("There was an error: ", ex);
		// Add conditional logic to show different status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
