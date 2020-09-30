package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ControlLogin;
import com.example.demo.repository.ControlLoginRepository;

@Service
@Transactional
public class ControlLoginService {

	@Autowired
	ControlLoginRepository controlLoginRepository;

	public List<ControlLogin> findAllCharacter_login(){
		return controlLoginRepository.findAll();
	}
}
