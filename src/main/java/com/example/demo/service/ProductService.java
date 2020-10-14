package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public List<Product> findProducts(){

		return productRepository.findAll();
	}

	public String save(Product product) {
		productRepository.save(product);
		return "";
	}

	public Product findProductById(Integer productId) {
		return productRepository.findProductById(productId);
	}

	public String updateProductById(Integer productId,String productType,String productIntro,
			String status,Double sales,Double cost,Integer stock,Timestamp timestamp) {
		productRepository.updateProductById(productId,productType,productIntro,status,sales,cost,stock,timestamp);
		return "";
	}

	public String deleteProductById(Integer productId,String status) {
		productRepository.deleteProductById(productId,status);
		return "";
	}

	//出品中商品を全表示
	public List<Product> findProductsByStatus(String status){
		return productRepository.findProductsByStatus(status);
	}

}
