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

	//orderをすべて検索
	public List<Order> findAllOrders() {
		return orderRepository.findAll();
	}

	//保存
	public String save(Order order) {
		orderRepository.save(order);
		return "";
	}

	//Idで一件検索
	public Order findOrderByOrderId(String orderId) {
		return orderRepository.findOrderByOrderId(orderId);
	}

	//削除
	public String deleteOrder(Order order) {
		orderRepository.delete(order);
		return "";
	}

	//orderIdか商品名で曖昧検索
	public List<Order> findAllOrderById(String orderId) {
		return orderRepository.findAllOrderById(orderId);
	}

	//idとユーザー名でorder検索
	public List<Order> findAllOrderByIdAndUserName(String orderId, String userName) {
		return orderRepository.findAllOrderByIdAndUserName(orderId,userName);
	}

	//ユーザー名でorder検索
	public List<Order> findAllOrderByUserName(String userName) {
		return orderRepository.findAllOrderByUserName(userName);
	}

	//支払金額で検索
	public List<Order> findAllOrderByPayment(Double payment1, Double payment2) {
		return orderRepository.findAllOrderByPayment(payment1, payment2);
	}

	//住所の曖昧検索
	public List<Order> findAllOrderByAddress(String address) {
		return orderRepository.findAllOrderByAddress(address);
	}

}
