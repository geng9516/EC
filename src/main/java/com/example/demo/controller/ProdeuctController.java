package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		System.out.println(productList);
		model.addAttribute("productList",productList);
 		return "productAll";
	}

	@RequestMapping("/productAdd")
	public String productAdd(Model model) {
		return "productAdd";
	}

	@RequestMapping("/saveProduct")
	public RedirectView saveProduct(
			@RequestParam(name = "productType")String productType,
			@RequestParam(name = "productIntro")String productIntro,
			@RequestParam(name = "status")String status,
			@RequestParam(name = "sales")Double sales,
			@RequestParam(name = "cost")Double cost,
			@RequestParam(name = "stock")Integer stock
			) {
		RedirectView redirectTarget = new RedirectView();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Product product = new Product();
		product.setProductType(productType);
		product.setProductIntro(productIntro);
		product.setStatus(status);
		product.setSales(sales);
		product.setCost(cost);
		product.setStock(stock);
		product.setDateCreated(timestamp);
		productService.save(product);
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	@RequestMapping("/productEdit")
	public String productEdit(
			Model model,
			@RequestParam(name = "productId")Integer productId
			) {
		Product product = productService.findProductById(productId);
		model.addAttribute("product",product);
		return "productEdit";
	}

	@RequestMapping("/editProduct")
	public RedirectView editProduct(
			@RequestParam(name = "productId")Integer productId,
			@RequestParam(name = "productType")String productType,
			@RequestParam(name = "productIntro")String productIntro,
			@RequestParam(name = "status")String status,
			@RequestParam(name = "sales")Double sales,
			@RequestParam(name = "cost")Double cost,
			@RequestParam(name = "stock")Integer stock
			) {
		RedirectView redirectTarget = new RedirectView();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		productService.updateProductById(productId,productType,productIntro,status,sales,cost,stock,timestamp);
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	@RequestMapping("/deleteProduct")
	public RedirectView deleteProduct(
			@RequestParam(name = "productId")Integer productId) {
		RedirectView redirectTarget = new RedirectView();
		productService.deleteProductById(productId,"出品停止");
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	@RequestMapping("/backProductAll")
	public RedirectView backProductAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}


}
