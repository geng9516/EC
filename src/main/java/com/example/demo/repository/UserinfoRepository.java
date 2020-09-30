package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Userinfo;

@Repository
public interface UserinfoRepository extends JpaRepository<Userinfo, Integer> {

}
