package com.microservice.demo.controller;

import java.util.List;

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

import com.microservice.demo.entity.ItemDTO;
import com.microservice.demo.service.ItemService;

@RestController
@RequestMapping("/items")
@RestControllerAdvice
public class ItemController {
	@Autowired
	private ItemService itemService;

	private static final Logger LOG = LoggerFactory.getLogger(ItemController.class);

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/item")
	public List<ItemDTO> all() {
		return itemService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/item/{itemName}")
	public ItemDTO get(@PathVariable String itemName) {
		return itemService.get(itemName);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/items/{itemName}")
	public ItemDTO put(@PathVariable String itemName, @RequestBody ItemDTO itemDTO) {
		ItemDTO itemDto = itemService.update(itemDTO, itemName);
		return itemDto;
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/items/{id}")
	public void delete(@PathVariable long id) {
		itemService.delete(id);
	}

	@PostMapping("/items/")
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDTO add(@RequestBody ItemDTO itemDTO) {
		return itemService.save(itemDTO);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleException(Throwable ex) {
		LOG.error("There was an error: ", ex);
		// Add conditional logic to show differnt status on different exceptions
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
