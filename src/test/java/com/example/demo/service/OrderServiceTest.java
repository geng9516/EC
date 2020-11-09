package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Order;
import com.example.demo.util.RandomOrderNumber;

@Transactional
@SpringBootTest
class OrderServiceTest {

	@Autowired
	private OrderService orderService;

	//orderをすべて検索
	@Test
	void testFindAllOrders() {
		List<Order> orderList = orderService.findAllOrders();
		assertEquals(4, orderList.size());
	}

	//保存
	@Test
	void testSave() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowTimeStr = sdf.format(timestamp);
		RandomOrderNumber ron = new RandomOrderNumber();
		Order order = new Order();
		order.setOrderId(nowTimeStr + ron.random());
		orderService.save(order);
		List<Order> orderList = orderService.findAllOrders();
		assertEquals(5, orderList.size());
	}

	//Idで一件検索
	@Test
	void testFindOrderByOrderId() {
		Order order = orderService.findOrderByOrderId("20201106265168");
		assertEquals(1, order.getId());
	}

	/*//削除
	@Test
	void testDeleteOrder() {
		Order order = orderService.findOrderByOrderId("20201106265168");
		order.setProduct(null);
		orderService.deleteOrder(order);
		List<Order> orderList = orderService.findAllOrders();
		assertEquals(0, orderList.size());
	}*/

	//orderIdか商品名で曖昧検索
	@Test
	void testFindAllOrderById() {
		List<Order> orderList = orderService.findAllOrderById("2");
		assertEquals(4, orderList.size());
	}

	//idとユーザー名でorder検索
	@Test
	void testFindAllOrderByIdAndUserName() {
		List<Order> orderList = orderService.findAllOrderByIdAndUserName("7","l");
		assertEquals(3, orderList.size());
	}

	//ユーザー名でorder検索
	@Test
	void testFindAllOrderByUserName() {
		List<Order> orderList = orderService.findAllOrderByUserName("z");
		assertEquals(1, orderList.size());
	}

	//支払金額で検索
	@Test
	void testFindAllOrderByPayment() {
		List<Order> orderList = orderService.findAllOrderByPayment(Double.valueOf(1), Double.valueOf(12000));
		assertEquals(4, orderList.size());
	}

	//住所の曖昧検索
	@Test
	void testFindAllOrderByAddress() {
		List<Order> orderList = orderService.findAllOrderByAddress("市");
		assertEquals(4, orderList.size());
	}

}
