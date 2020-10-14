package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	/*
	注文
	*/
	@RequestMapping("/orderAll")
	public String orderAll(Model model) {
		List<Order> orderList = orderService.findAllOrders();
		model.addAttribute("orderList", orderList);
		return "orderAll";
	}

	@RequestMapping("/checkOrder")
	public RedirectView checkOrder(
			@RequestParam(name="orderId")String orderId
			) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Order order = orderService.findOrderByOrderId(orderId);
		order.setStatusByOrder("注文処理済");
		order.setConfirmationTime(timestamp);
		orderService.save(order);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("orderAll");
		return redirectTarget;
	}

	@RequestMapping("/orderEdit")
	public RedirectView orderEdit(
			@RequestParam(name="orderId")String orderId
			) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Order order = orderService.findOrderByOrderId(orderId);
		order.setStatusByBuy("支払確認済");
		order.setDateModified(timestamp);
		orderService.save(order);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("orderAll");
		return redirectTarget;
	}

	@RequestMapping("/deleteOrder")
	public RedirectView deleteOrder(
			@RequestParam(name="orderId")String orderId
			) {
		Order order = orderService.findOrderByOrderId(orderId);
		orderService.deleteOrder(order);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("orderAll");
		return redirectTarget;
	}

	@RequestMapping("/backOrderAll")
	public String backOrderAll(Model model) {
		return "orderAll";
	}

}
