package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Character_login;
import com.example.demo.repository.Character_loginRepository;

@Service
@Transactional
public class Character_loginService {

	@Autowired
	Character_loginRepository character_loginRepository;

	public List<Character_login> findAllCharacter_login(){
		return character_loginRepository.findAll();
	}
}
