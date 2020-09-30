package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Userinfo;
import com.example.demo.repository.UserinfoRepository;

@Service
@Transactional
public class UserinfoService {

	@Autowired
	UserinfoRepository userinfoRepository;

	public List<Userinfo> findUserinfos(){

		return userinfoRepository.findAll();
	}

}
