package com.microservices.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderLineItemDTO {
	private long id;
	private String itemName;
	private long itemQuantity;
}
