package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Order;

//SELECT a FROM Account WHERE a.firstName LIKE %?1%

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

	//Idで一件検索
	@Query("select o from Order o where o.orderId=?1")
	Order findOrderByOrderId(String orderId);

	//orderIdか商品名で曖昧検索
	@Query("select o from Order o where o.orderId like %?1%")
	List<Order> findAllOrderById(String orderId);

	//idとユーザー名でorder検索
	@Query("select o from Order o where o.orderId like %?1% or o.user.userName like %?2%")
	List<Order> findAllOrderByIdAndUserName(String orderId, String userName);

	//ユーザー名でorder検索
	@Query("select o from Order o where o.user.userName like %?1%")
	List<Order> findAllOrderByUserName(String userName);

	//支払金額で検索
	@Query("select o from Order o where o.payment between concat(:payment1,'%') and concat(:payment2,'%')")
	List<Order> findAllOrderByPayment(@Param("payment1") Double payment1, @Param("payment2") Double payment2);

	//住所の曖昧検索
	@Query("select o from Order o where o.address like %?1%")
	List<Order> findAllOrderByAddress(String address);

}
