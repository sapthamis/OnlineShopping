package com.microservice.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.demo.entity.Item;
import com.microservice.demo.entity.ItemDTO;
import com.microservice.demo.repository.ItemRepository;

@Service
public class ItemService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ItemRepository itemRepository;

	private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

	public List<ItemDTO> all() {
		return itemRepository.findAll().stream().map(item -> modelMapper.map(item, ItemDTO.class))
				.collect(Collectors.toList());
	}

	public ItemDTO save(ItemDTO itemDTO) {
		Item customer = itemRepository.save(modelMapper.map(itemDTO, Item.class));
		return modelMapper.map(customer, ItemDTO.class);
	}

	public ItemDTO update(ItemDTO itemdto, String name) {
		Item item = itemRepository.findByName(name);
		item.setName(itemdto.getName());
		item.setDescription(itemdto.getDescription());
		item.setPrice(itemdto.getPrice());
		Item updatedItem = itemRepository.save(item);
		return modelMapper.map(updatedItem, ItemDTO.class);
	}

	public ItemDTO get(String itemName) {
		Item item = itemRepository.findByName(itemName);
		return modelMapper.map(item, ItemDTO.class);
	}

	public void delete(long itemId) {
		itemRepository.deleteById(itemId);
	}

}
