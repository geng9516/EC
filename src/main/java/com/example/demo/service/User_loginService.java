package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.User_login;
import com.example.demo.repository.User_loginRepository;

@Service
@Transactional
public class User_loginService {

	@Autowired
	User_loginRepository user_loginRepository;

	public List<User_login> findUser_login() {
		return user_loginRepository.findAll();
	}
}
