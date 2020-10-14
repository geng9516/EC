package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.id=?1")
	Product findProductById(Integer productId);

	@Modifying
	@Query("update Product p set p.productType=?2,p.productIntro=?3,p.status=?4,p.sales=?5,p.cost=?6,p.dateModified=?8,p.stock=?7 where p.id=?1")
	void updateProductById(Integer productId, String productType, String productIntro,
			String status, Double sales, Double cost, Integer stock, Timestamp timestamp);

	@Modifying
	@Query("update Product p set p.status=?2 where p.id=?1")
	void deleteProductById(Integer productId, String status);

	@Query("select p from Product p where p.status=?1")
	List<Product> findProductsByStatus(String status);
}
