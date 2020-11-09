package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
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
	/**order一覧表示
	 * @param model
	 * @return
	 */
	@RequestMapping("/orderAll")
	public String orderAll(Model model) {

		List<Order> orderList = orderService.findAllOrders();
		for(Order order:orderList) {
			System.out.println(order.getUser());
		}
		model.addAttribute("orderList", orderList);
		return "orderAll";
	}

	/**注文処理済確認
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/checkOrder")
	public RedirectView checkOrder(
			@RequestParam(name = "orderId") String orderId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Order order = orderService.findOrderByOrderId(orderId);
		order.setStatusByOrder("注文処理済");
		order.setConfirmationTime(timestamp);
		orderService.save(order);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("orderAll");
		return redirectTarget;
	}

	/**支払確認済確認
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/orderEdit")
	public RedirectView orderEdit(
			@RequestParam(name = "orderId") String orderId) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Order order = orderService.findOrderByOrderId(orderId);
		order.setStatusByBuy("支払確認済");
		order.setDateModified(timestamp);
		orderService.save(order);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("orderAll");
		return redirectTarget;
	}

	/**曖昧検索
	 * @param model
	 * @param orderId
	 * @param userName
	 * @param payment1
	 * @param payment2
	 * @param address
	 * @return
	 */
	@RequestMapping("/searchOrder")
	public String searchOrder(Model model,
			@RequestParam(name = "orderId") String orderId,
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "payment1") Double payment1,
			@RequestParam(name = "payment2") Double payment2,
			@RequestParam(name = "address") String address) {
		List<Order> orderList = new ArrayList<>();
		if (!orderId.isEmpty()) {
			//orderIdか商品名で曖昧検索
			orderList = orderService.findAllOrderById(orderId);
			if (!userName.isEmpty()) {
				//idとユーザー名でorder検索
				orderList = orderService.findAllOrderByIdAndUserName(orderId,userName);
			}
		} else if (!userName.isEmpty()) {
			//ユーザー名でorder検索
			orderList = orderService.findAllOrderByUserName(userName);
		} else if (payment1 != null && payment2 != null) {
			//支払金額で検索
			orderList = orderService.findAllOrderByPayment(payment1, payment2);
		} else if (!address.isEmpty()) {
			//住所の曖昧検索
			orderList = orderService.findAllOrderByAddress(address);
		} else {
			orderList = orderService.findAllOrders();
		}
		model.addAttribute("orderList", orderList);
		return "orderAll";
	}

	/**削除
	 * @param orderId
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	public RedirectView deleteOrder(
			@RequestParam(name = "orderId") String orderId) {
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
