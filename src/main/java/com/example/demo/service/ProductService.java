package com.example.demo.service;

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

	//すべて検索
	public List<Product> findProducts() {
		return productRepository.findAll();
	}

	//保存
	public String save(Product product) {
		productRepository.save(product);
		return "";
	}

	//商品IDで取得
	public Product findProductById(Integer productId) {
		return productRepository.findProductById(productId);
	}

	//productIdで状態を更新する
	public String deleteProductById(Integer productId, String status) {
		productRepository.deleteProductById(productId, status);
		return "";
	}

	//出品中商品を全表示
	public List<Product> findProductsByStatus(String status) {
		return productRepository.findProductsByStatus(status);
	}

	//productIdで曖昧検索
	public List<Product> findAllById(Integer productId) {
		return productRepository.findAllById(productId);
	}

	//productIdと商品情報で曖昧検索
	public List<Product> findAllByIdAndProductIntro(Integer productId,String productIntro) {
		return productRepository.findAllByIdAndProductIntro(productId,productIntro);
	}

	//商品情報で曖昧検索
	public List<Product> findAllByProductIntro(String productIntro) {
		return productRepository.findAllByProductIntro(productIntro);
	}

	//商品タイプで曖昧検索
	public List<Product> findAllByProductType(String productType) {
		return productRepository.findAllByProductType(productType);
	}

	//価格で曖昧検索(使ってない)
	public List<Product> findAllBySales(Double sales1) {
		return productRepository.findAllBySales(sales1);
	}

	//sales1とsales2間の価格検索
	public List<Product> findAllByBetweenSales1AndSales2(Double sales1, Double sales2) {
		return productRepository.findAllByBetweenSales1AndSales2(sales1,sales2);
	}

	//コスト値で曖昧検索
	public List<Product> findAllByCost(Double cost1, Double cost2) {
		return productRepository.findAllByCost(cost1,cost2);
	}

	//在庫で曖昧検索
	public List<Product> findAllByStock(Integer stock1, Integer stock2) {
		return productRepository.findAllByStock(stock1,stock2);
	}

}
