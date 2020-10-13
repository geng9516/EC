package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	/*
	注文
	*/
	@RequestMapping("/orderAll")
	public String orderAll(Model model) {
		return "orderAll";
	}

	@RequestMapping("/orderEdit")
	public String orderEdit(Model model) {
		return "orderAll";
	}

	@RequestMapping("/backOrderAll")
	public String backOrderAll(Model model) {
		return "orderAll";
	}

}
