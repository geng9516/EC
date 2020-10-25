package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Cart;

@Repository
public interface CartRepositroy extends JpaRepository<Cart,Integer>{


	@Query("select c from Cart c where c.uId=?1")
	List<Cart> fingCartById(Integer userId);

	@Query("select c from Cart c where c.pId=?1")
	Cart findCartByPid(Integer pId);

	@Modifying
	@Query("delete from Cart c where c.pId=?1")
	void deleteByProductId(Integer productId);

	//個人のカート内容の特定商品を削除
	@Modifying
	@Query("delete from Cart c where c.pId=?1 and c.uId=?2")
	void deleteByProductIdAndUserId(Integer productId, Integer userId);

	//userIdで特定の商品を取得
	@Query("select c from Cart c where c.pId=?1 and c.uId=?2")
	Cart findByProductIdAndUserId(Integer productId, Integer userId);
}
