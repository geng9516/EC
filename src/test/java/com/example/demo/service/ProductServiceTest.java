package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Product;
@SpringBootTest
@Transactional
class ProductServiceTest {

	@Autowired
	private ProductService productService;

	//すべて検索
	@Test
	void testFindProducts() {
		List<Product> productList = productService.findProducts();
		assertEquals(1, productList.size());
	}

	//保存
	@Test
	void testSave() {
		Product product = new Product();
		product.setProductType("洋服");
		product.setProductIntro("ドレス");
		product.setSales(Double.valueOf(12000));
		product.setCost(Double.valueOf(1000));
		product.setStock(50);
		product.setStatus("出品中");
		productService.save(product);
		List<Product> productList = productService.findProducts();
		assertEquals(2, productList.size());
	}

	//商品IDで取得
	@Test
	void testFindProductById() {
		Product product = productService.findProductById(1);
		assertEquals("腕時計",product.getProductType());
	}

	//productIdで状態を更新する
	@Test
	void testDeleteProductById() {
		productService.deleteProductById(1, "出品停止");
		Product product = productService.findProductById(1);
		assertEquals("出品停止",product.getStatus());
	}

	//出品中商品を全表示
	@Test
	void testFindProductsByStatus() {
		List<Product> productList = productService.findProductsByStatus("出品中");
		assertEquals(1, productList.size());
	}

	//productIdで曖昧検索
	@Test
	void testFindAllById() {
		List<Product> productList = productService.findAllById(1);
		assertEquals(1, productList.size());
	}

	//productIdと商品情報で曖昧検索
	@Test
	void testFindAllByIdAndProductIntro() {
		List<Product> productList = productService.findAllByIdAndProductIntro(1, "腕");
		assertEquals(1, productList.size());
	}

	//商品情報で曖昧検索
	@Test
	void testFindAllByProductIntro() {
		List<Product> productList = productService.findAllByProductIntro("アニ");
		assertEquals(1, productList.size());
	}

	//商品タイプで曖昧検索
	@Test
	void testFindAllByProductType() {
		List<Product> productList = productService.findAllByProductType("腕");
		assertEquals(1, productList.size());
	}

	//sales1とsales2間の価格検索
	@Test
	void testFindAllByBetweenSales1AndSales2() {
		List<Product> productList = productService.findAllByBetweenSales1AndSales2(Double.valueOf(1), Double.valueOf(12000));
		assertEquals(1, productList.size());
	}

	//コスト値で曖昧検索
	@Test
	void testFindAllByCost() {
		List<Product> productList = productService.findAllByCost(Double.valueOf(1), Double.valueOf(12000));
		assertEquals(1, productList.size());
	}

	//在庫で曖昧検索
	@Test
	void testFindAllByStock() {
		List<Product> productList = productService.findAllByStock(1, 100);
		assertEquals(1, productList.size());
	}

	//すべての要素で曖昧検索
	@Test
	void testFindAllProductByAnything() {
		List<Product> productList = productService.findAllProductByAnything("アニ");
		assertEquals(1, productList.size());
	}

}
