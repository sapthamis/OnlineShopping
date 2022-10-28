package com.microservice.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.demo.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	public Item findByName(String name);

}
