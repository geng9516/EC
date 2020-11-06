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

	public List<ControlLogin> findAllControlLogin() {
		return controlLoginRepository.findAll();
	}

	public String saveControlLogin(ControlLogin controlLogin) {
		controlLoginRepository.save(controlLogin);
		return "";
	}

	//ログインユーザー存在するかを判断
	public Boolean findExistLoginUser(String userId, String pass) {
		Boolean flog = false;
		if (controlLoginRepository.findExistLoginUser(userId, pass) != null) {
			flog = true;
		}
		return flog;
	}

	//IDとパスに合った管理者をreturn
	public ControlLogin findLoginUser(String loginId, String password) {
		return controlLoginRepository.findExistLoginUser(loginId, password);
	}

	//シーケンスIDで検索
	public ControlLogin findControlLoginById(Integer controlLoginId) {
		return controlLoginRepository.findControlLoginById(controlLoginId);
	}

	//削除
	public String deleteControlLogin(ControlLogin controlLogin) {
		controlLoginRepository.delete(controlLogin);
		return "";
	}

	public String updateCotrolByid(Integer id, String password) {
		controlLoginRepository.updateCotrolByid(id, password);
		return "";
	}

}
