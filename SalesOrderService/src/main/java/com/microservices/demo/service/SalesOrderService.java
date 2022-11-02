package com.microservices.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.demo.dto.SalesOrderDTO;

import com.microservices.demo.entity.SalesOrder;
import com.microservices.demo.repository.SalesOrderRepository;

@Service
public class SalesOrderService {
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private SalesOrderRepository salesOrderRepository;

	public List<SalesOrderDTO> all() {
		return salesOrderRepository.findAll().stream().map(c -> modelMapper.map(c, SalesOrderDTO.class))
				.collect(Collectors.toList());
	}

	public SalesOrderDTO save(SalesOrderDTO salesOrderDTO) {
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
