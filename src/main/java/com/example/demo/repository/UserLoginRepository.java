package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserLogin;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,Integer>{

}
