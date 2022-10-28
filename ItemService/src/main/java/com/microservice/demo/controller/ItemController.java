package com.microservice.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.demo.entity.ItemDTO;
import com.microservice.demo.service.ItemService;


@RestController
@RequestMapping("/items")
public class ItemController {
	@Autowired
	private ItemService itemService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/")
	public List<ItemDTO> all() {
		return itemService.all();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{itemName}")
	public ItemDTO get(@PathVariable String itemName) {
		return itemService.get(itemName);
	}

//	@ResponseStatus(HttpStatus.OK)
//	@PutMapping("/{itemName}")
//	public ItemDTO put(@PathVariable String itemName,@RequestBody ItemDTO itemDTO) {
//		itemDTO.setName(itemName);
//		return itemService.save(itemDTO);
//	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{itemName}")
	public ItemDTO put(@PathVariable String itemName,@RequestBody ItemDTO itemDTO) {
	   ItemDTO itemDto=itemService.update(itemDTO, itemName);
	   return itemDto;
	}
	
	
	

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		itemService.delete(id);
	}

	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public ItemDTO add(@RequestBody ItemDTO itemDTO) {
		return itemService.save(itemDTO);
	}


}
