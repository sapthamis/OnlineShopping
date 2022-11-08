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
import com.microservices.demo.dto.OrderLineItemDTO;
import com.microservices.demo.dto.SalesOrderDTO;
import com.microservices.demo.entity.OrderLineItem;
import com.microservices.demo.entity.SalesOrder;
import com.microservices.demo.feignclient.ItemFeignClient;
import com.microservices.demo.repository.SalesOrderRepository;

@Service
public class SalesOrderService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ItemFeignClient itemFeignClient;

	private static final Logger logger = LoggerFactory.getLogger(SalesOrderService.class);

//	@Autowired
//	private OrderLineItem orderLineItem;

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	public List<SalesOrderDTO> all() {
		return salesOrderRepository.findAll().stream().map(c -> modelMapper.map(c, SalesOrderDTO.class))
				.collect(Collectors.toList());
	}

	public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
		List<OrderLineItem> orderLineItem = salesOrderDTO.getOrderLineItems();
		for (int i = 0; i < orderLineItem.size(); i++) {
			Optional<ItemServiceDTO> items = Optional.of((itemFeignClient.get(orderLineItem.get(i).getItemName())));
			logger.info("items "+items);	
		}
		Date date = new Date();
		salesOrderDTO.setOrderDate(date);
		SalesOrder salesOrder = salesOrderRepository.save(modelMapper.map(salesOrderDTO, SalesOrder.class));
		return modelMapper.map(salesOrder, SalesOrderDTO.class);

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

	public void delete(long orderId) {
		salesOrderRepository.deleteById(orderId);
	}

}
