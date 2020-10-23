package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Product;

//SELECT a FROM Account WHERE a.firstName LIKE %?1%

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	//商品IDで取得
	@Query("select p from Product p where p.id=?1")
	Product findProductById(Integer productId);

	//productIdで状態を更新する
	@Modifying
	@Query("update Product p set p.status=?2 where p.id=?1")
	void deleteProductById(Integer productId, String status);

	@Query("select p from Product p where p.status=?1")
	List<Product> findProductsByStatus(String status);

	//productIdで曖昧検索
	@Query("select p from Product p where p.id like concat(:productId,'%')")
	List<Product> findAllById(@Param("productId")Integer productId);

	//productIdと商品情報で曖昧検索
	@Query("select p from Product p where p.id like concat(:productId,'%') or p.productIntro like CONCAT(:productIntro,'%')")
	List<Product> findAllByIdAndProductIntro(@Param("productId")Integer productId,@Param("productIntro")String productIntro);

	//商品情報で曖昧検索
	@Query("select p from Product p where p.productIntro like concat(:productIntro,'%')")
	List<Product> findAllByProductIntro(@Param("productIntro")String productIntro);

	//商品タイプで曖昧検索
	@Query("select p from Product p where p.productType like %?1%")
	List<Product> findAllByProductType(String productType);

	//価格で曖昧検索
	@Query("select p from Product p where p.sales like concat(:sales1,'%')")
	List<Product> findAllBySales(@Param("sales1")Double sales1);

	//sales1とsales2間の価格検索
	@Query("select p from Product p where p.sales between concat(:sales1,'%') and concat(:sales2,'%')")
	List<Product> findAllByBetweenSales1AndSales2(@Param("sales1")Double sales1,@Param("sales2")Double sales2);

	//コスト値で曖昧検索
	@Query("select p from Product p where p.cost between concat(:cost1,'%') and concat(:cost2,'%')")
	List<Product> findAllByCost(@Param("cost1")Double cost1,@Param("cost2")Double cost2);

	//在庫で曖昧検索
	@Query("select p from Product p where p.stock between concat(:stock1,'%') and concat(:stock2,'%')")
	List<Product> findAllByStock(@Param("stock1")Integer stock1,@Param("stock2")Integer stock2);
}
