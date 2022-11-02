package com.microservices.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservices.demo.entity.SalesOrder;

@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder,Long> {

}
