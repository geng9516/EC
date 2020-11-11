package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cart;
import com.example.demo.repository.CartRepositroy;

@Service
@Transactional
public class CartService {

	@Autowired
	private CartRepositroy cartRepositroy;

	//カート内すべての商品を取得
	public List<Cart> findCartAll() {
		return cartRepositroy.findAll();
	}

	//新規保存
	public String save(Cart cart) {
		cartRepositroy.save(cart);
		return "";
	}

	public List<Cart> findCartById(Integer userId) {
		return cartRepositroy.fingCartById(userId);
	}

	public Cart findCartByPid(Integer pId) {
		return cartRepositroy.findCartByPid(pId);
	}

	public String deleteByProductId(Integer productId) {
		cartRepositroy.deleteByProductId(productId);
		return "";
	}

	//個人のカート内容の特定商品を削除
	public void deleteByProductIdAndUserId(Integer productId, Integer userId) {
		cartRepositroy.deleteByProductIdAndUserId(productId,userId);
	}

	//userIdで特定の商品を取得
	public Cart findByProductIdAndUserId(Integer productId, Integer userId) {
		return cartRepositroy.findByProductIdAndUserId(productId,userId);
	}
}
