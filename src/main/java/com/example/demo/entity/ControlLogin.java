package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

import lombok.Data;

@Data
@Entity
@Transactional
public class ControlLogin {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String Login_id;
	private String pass;
	private Control control;
}
