package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Character_login;

@Repository
public interface Character_loginRepository extends JpaRepository<Character_login,Integer> {

}
