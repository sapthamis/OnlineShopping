package com.microservices.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.demo.dto.ItemServiceDTO;

import com.microservices.demo.dto.SalesOrderDTO;
import com.microservices.demo.entity.Customer;
import com.microservices.demo.entity.OrderLineItem;
import com.microservices.demo.entity.SalesOrder;

import com.microservices.demo.feignclient.ItemFeignClient;
import com.microservices.demo.repository.CustomerSORepository;
import com.microservices.demo.repository.SalesOrderRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class SalesOrderService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private CustomerSORepository customerSORepository;

	@Autowired
	private ItemFeignClient itemFeignClient;
	// private Resilience4jConfiguration resilience;

	private static final Logger logger = LoggerFactory.getLogger(SalesOrderService.class);

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	public List<SalesOrderDTO> all() {
		return salesOrderRepository.findAll().stream().map(c -> modelMapper.map(c, SalesOrderDTO.class))
				.collect(Collectors.toList());
	}

	@CircuitBreaker(name = "Item", fallbackMethod = "fallbackMethod")
	public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
		Long sales = salesOrderDTO.getCustId();
		try {
			Optional<Customer> customer = Optional.of(customerSORepository.getById(sales));
			logger.info("customer " + customer);
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
		List<OrderLineItem> orderLineItem = salesOrderDTO.getOrderLineItems();
		for (int i = 0; i < orderLineItem.size(); i++) {
			Optional<ItemServiceDTO> items = Optional.of((itemFeignClient.get(orderLineItem.get(i).getItemName())));
			logger.info("items " + items);
		}
		Date date = new Date();
		salesOrderDTO.setOrderDate(date);
		SalesOrder salesOrder = salesOrderRepository.save(modelMapper.map(salesOrderDTO, SalesOrder.class));
		logger.info("salesOrder " + salesOrder);
		return modelMapper.map(salesOrder, SalesOrderDTO.class);

	}

	public SalesOrderDTO fallbackMethod(SalesOrderDTO salesOrderDTO, Throwable th) {
		logger.error("Error = " + th);
		return new SalesOrderDTO();

	}

	public SalesOrderDTO update(SalesOrderDTO salesOrderDTO, Long id) {
		SalesOrder salesOrder = salesOrderRepository.getById(id);
		Date date = new Date();
		salesOrder.setOrderDate(date);
		salesOrder.setOrderDesc(salesOrderDTO.getOrderDesc());
		salesOrder.setTotalPrice(salesOrderDTO.getTotalPrice());
		salesOrder.setOrderLineItems(salesOrderDTO.getOrderLineItems());
		SalesOrder updatedSalesOrder = salesOrderRepository.save(salesOrder);
		return modelMapper.map(updatedSalesOrder, SalesOrderDTO.class);

	}

	public SalesOrderDTO get(long orderId) {
		Optional<SalesOrder> orderResult = salesOrderRepository.findById(orderId);
		if (!orderResult.isPresent()) {
			return null;
		}
		return modelMapper.map(orderResult.get(), SalesOrderDTO.class);
	}
	
	public SalesOrderDTO getByCustomerId(long customerId) {
		Optional<SalesOrder> orderResult=salesOrderRepository.findByCustId(customerId);
		if(!orderResult.isPresent()) {
			return null;
		}
		logger.info("getorderbycustomer "+orderResult);
		return modelMapper.map(orderResult.get(), SalesOrderDTO.class);
		
	}

	public void delete(long orderId) {
		salesOrderRepository.deleteById(orderId);
	}

}
