package com.microservice.demo.entity;

import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
	private long id;
	private String description;
	private String name;
	private double price;
}
