package com.microservices.demo.dto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemServiceDTO{
		private long id;
		private String description;
		private String name;
		private double price;
}
