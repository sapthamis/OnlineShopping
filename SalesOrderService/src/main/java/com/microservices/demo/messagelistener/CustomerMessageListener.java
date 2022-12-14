package com.microservices.demo.messagelistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.microservices.demo.service.CustomerService;


@Service
public class CustomerMessageListener {

	@Autowired
	private CustomerService customerService;

	@KafkaListener(topics = "CustomerCreated")
	public void handle(String json) {
		Gson gson =new Gson();
		Customer customer = gson.fromJson(json, Customer.class);
		System.out.println("Received: " + customer);
		customerService.save(customer);
	}
	
	public static class Customer {

		private long id;
		private String email;
		private String firstName;
		private String lastName;
		
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public Customer() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Customer(long id, String email, String firstName, String lastName) {
			super();
			this.id = id;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
					+ "]";
		}
		

}
}
