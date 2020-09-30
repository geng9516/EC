package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Order;
import com.example.demo.repository.OrderRepository;

@Service
@Transactional
public class OrderService {

	@Autowired
	OrderRepository orderRepository;

	public List<Order> findAllOrders(){

		return orderRepository.findAll();
	}

}
