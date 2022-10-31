package com.microservices.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CUSTOMER")
public class Customer {

	@Id
	@GeneratedValue
	private long id;

	@NotEmpty
	@Email
	private String email;

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;

}
