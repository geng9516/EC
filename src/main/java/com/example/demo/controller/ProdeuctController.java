package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@Controller
public class ProdeuctController {

	@Autowired
	private ProductService productService;
	/*
	商品
	*/
	@RequestMapping("/productAll")
	public String productAll(Model model) {
		List<Product> productList = productService.findProducts();
		model.addAttribute("product",productList);
 		return "productAll";
	}

	@RequestMapping("/productAdd")
	public String productAdd(Model model) {
		return "productAdd";
	}

	@RequestMapping("/saveProduct")
	public RedirectView saveProduct() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	@RequestMapping("/productEdit")
	public String productEdit(Model model) {
		return "productEdit";
	}

	@RequestMapping("/backProductAll")
	public String backProductAll(Model model) {
		return "productAll";
	}


}
