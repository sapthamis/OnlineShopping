package com.microservices.demo.dto;

import java.util.Date;
import java.util.List;

import com.microservices.demo.entity.OrderLineItem;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesOrderDTO {
	private long id;
	private Date orderDate;
	// private long custId;
	private String orderDesc;
	private double totalPrice;
	
	private List<OrderLineItem> orderLineItems;

}
