package com.microservices.demo.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String email;
	private String firstName;
	private String lastName;
}
