package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User_login;

@Repository
public interface User_loginRepository extends JpaRepository<User_login,Integer>{

}
