package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,Integer>{

	@Query("select u from UserLogin u where u.loginId=?1 and u.pass=?2")
	UserLogin findUserByLoginId(String loginId,String password);

	//判断
	@Query("select u from UserLogin u where u.loginId=?1")
	UserLogin findUserLoginById(String userId);

	//LoginIdでuserLoginを取得
	@Query("select u from UserLogin u where u.loginId=?1")
	UserLogin findUserLoginByLoginId(String userLoginId);
}

