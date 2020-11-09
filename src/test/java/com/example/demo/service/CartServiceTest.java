package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cart;

@SpringBootTest
@Transactional
class CartServiceTest {

	@Autowired
	private CartService cartService;

	//カート内すべての商品を取得
	@Test
	void testFingCartAll() {
		List<Cart> cartList = cartService.fingCartAll();
		assertEquals(1, cartList.size());
	}

	//新規保存
	@Test
	void testSave() {
		Cart cart = new Cart();
		cart.setpId(2);
		cart.setuId(3);
		cart.setProductIntro("腕時計");
		cartService.save(cart);
		List<Cart> cartList = cartService.fingCartAll();
		assertEquals(2, cartList.size());
	}

	//同じユーザーのカート内容を取得
	@Test
	void testFingCartById() {
		List<Cart> cartList = cartService.fingCartById(3);
		assertEquals(1, cartList.size());
	}

	//ProductIdで取得
	@Test
	void testFindCartByPid() {
		Cart cart = cartService.findCartByPid(1);
		assertEquals("アニメ時計", cart.getProductIntro());
	}

	//ProductIdで削除
	@Test
	void testDeleteByProductId() {
		cartService.deleteByProductId(1);
		List<Cart> cartList = cartService.fingCartAll();
		assertEquals(0, cartList.size());
	}

	//個人のカート内容の特定商品を削除
	@Test
	void testDeleteByProductIdAndUserId() {
		cartService.deleteByProductIdAndUserId(1, 3);
		List<Cart> cartList = cartService.fingCartAll();
		assertEquals(0, cartList.size());
	}

	//userIdで特定の商品を取得
	@Test
	void testFindByProductIdAndUserId() {
		Cart cart = cartService.findByProductIdAndUserId(1, 3);
		assertEquals("アニメ時計", cart.getProductIntro());
	}

}
