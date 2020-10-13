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

	public String saveControlLogin(ControlLogin controlLogin) {
		controlLoginRepository.save(controlLogin);
		return "";
	}

	public ControlLogin findControlLoginByControlName(Integer controlId) {
		return controlLoginRepository.findControlLoginByControlName(controlId);
	}

	public String deleteControlLogin(ControlLogin controlLogin) {
		controlLoginRepository.delete(controlLogin);
		return "";
	}

	public String updateCotrolByid(Integer controlLoginId,String password) {
		controlLoginRepository.updateCotrolByid(controlLoginId,password);
		return "";
	}
}
