package com.microservices.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "SALES_ORDER")
public class SalesOrder {
	@Id
	@GeneratedValue
	private long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss z", timezone = "IST")
	private Date orderDate;

	 @NotNull
	private long custId;

	private String orderDesc;

	private double totalPrice;

	@OneToMany(targetEntity = OrderLineItem.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "order_id", referencedColumnName = "id")
	private List<OrderLineItem> orderLineItems;

}
