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

	public List<Cart> fingCartAll() {
		return cartRepositroy.findAll();
	}

	public String save(Cart cart) {
		cartRepositroy.save(cart);
		return "";
	}

	public List<Cart> fingCartById(Integer userId) {
		return cartRepositroy.fingCartById(userId);
	}

	public Boolean findCartByPid(Integer pId) {
		Boolean flog = false;
		Cart cart = cartRepositroy.findCartByPid(pId);
		if(cart != null) {
			flog=true;
		}
		return flog;
	}

	public String deleteByProductId(Integer productId) {
		cartRepositroy.deleteByProductId(productId);
		return "";
	}
}
