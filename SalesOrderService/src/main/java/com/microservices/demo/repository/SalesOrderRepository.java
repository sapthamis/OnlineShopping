package com.microservices.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.demo.entity.Customer;
import com.microservices.demo.entity.SalesOrder;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Long> {
	public Optional<SalesOrder> findByCustId(long custId);
}
