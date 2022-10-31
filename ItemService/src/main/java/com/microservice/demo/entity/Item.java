package com.microservice.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ITEM")
public class Item {

	@Id
	@GeneratedValue
	private long id;

	private String description;
	
	@NotNull
	private String name;

	@NotNull
	private double price;
	
	

}
