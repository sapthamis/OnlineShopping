package com.microservices.demo.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "CUSTOMER_SOS")
public class Customer {
	@Id
	private long id;

	@NotNull
	private String email;

	@NotNull
	private String firstName;

	@NotNull
	private String lastName;

}
