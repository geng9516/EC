package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserLogin;
import com.example.demo.repository.UserLoginRepository;

@Service
@Transactional
public class UserLoginService {

	@Autowired
	UserLoginRepository loginRepository;

	public List<UserLogin> findUser_login() {
		return loginRepository.findAll();
	}
}
