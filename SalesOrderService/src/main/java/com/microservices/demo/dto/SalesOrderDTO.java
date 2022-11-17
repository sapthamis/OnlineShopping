package com.microservices.demo.dto;

import java.util.Date;
import java.util.List;

import com.microservices.demo.entity.OrderLineItem;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesOrderDTO {
	private long id;
	private Date orderDate;
	private long custId;
	private String orderDesc;
	private double totalPrice;
	
	private List<OrderLineItem> orderLineItems;
	

}
