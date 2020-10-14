package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{


	@Query("select o from Order o where o.orderId=?1")
	Order findOrderByOrderId(String orderId);
}
