package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Control;

@Repository
public interface ControlRepository extends JpaRepository<Control,String>{


}
