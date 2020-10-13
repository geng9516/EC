package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Userinfo;
import com.example.demo.repository.UserinfoRepository;

@Service
@Transactional//事务
public class UserinfoService {

	@Autowired
	UserinfoRepository userinfoRepository;

	public List<Userinfo> findUserinfos(){

		return userinfoRepository.findAll();
	}

	public Userinfo findUser(Integer userId) {
		return userinfoRepository.findByUserId(userId);

	}

	public String saveUserinfo(Userinfo userinfo) {
		userinfoRepository.save(userinfo);
		return "";
	}

	public String deleteUser(Userinfo userinfo) {
		userinfoRepository.delete(userinfo);
		return "";
	}


}
