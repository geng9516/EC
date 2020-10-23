package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Userinfo;

//SELECT a FROM Account WHERE a.firstName LIKE %?1%

@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo, Integer>, JpaSpecificationExecutor<Userinfo>{//JpaSpecificationExecutor<Userinfo>動的クエリー実現ため

	//id検索
	@Query("select u from Userinfo u where u.id=?1")
	Userinfo findByUserId(Integer userId);

	//idとuserNameでとどっちに合った曖昧検索
	@Query("select u from Userinfo u where u.id like concat(:id,'%') or u.userName like concat(:userName,'%') order by u.id asc")
	List<Userinfo> findAllByAnything(@Param("id")Integer id,@Param("userName")String userName);

	//id曖昧検索
	@Query("select u from Userinfo u where u.id like concat(:id,'%') order by u.id asc")
	List<Userinfo> findAllById(@Param("id")Integer id);

	//userNameで曖昧検索
	@Query("select u from Userinfo u where u.userName like %?1% order by u.id asc")
	List<Userinfo> findAllByUserName(String userName);

	//ニックネームで曖昧検索
	@Query("select u from Userinfo u where u.userNickname like %?1% order by u.id asc")
	List<Userinfo> findAllByUserNickName(String userNickname);

	//telで曖昧検索
	@Query("select u from Userinfo u where u.tel like %?1% order by u.id asc")
	List<Userinfo> findAllByTel(String tel);

	//addressで曖昧検索
	@Query("select u from Userinfo u where u.address like %?1% order by u.id asc")
	List<Userinfo> findAllByAddress(String address);

	//emailで曖昧検索
	@Query("select u from Userinfo u where u.email like %?1% order by u.id asc")
	List<Userinfo> findAllByEmail(String email);
}
