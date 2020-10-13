package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Userinfo;

@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo, Integer> {

	@Query("select u from Userinfo u where u.id=?1")
	Userinfo findByUserId(Integer userId);
}
