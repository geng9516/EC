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

	//すべて検索
	public List<Userinfo> findUserinfos(){
		return userinfoRepository.findAll();
	}

	//id検索
	public Userinfo findUser(Integer id) {
		return userinfoRepository.findByUserId(id);
	}

	//保存
	public String saveUserinfo(Userinfo userinfo) {
		userinfoRepository.save(userinfo);
		return "";
	}

	//削除
	public String deleteUser(Userinfo userinfo) {
		userinfoRepository.delete(userinfo);
		return "";
	}

	//idとuserNameでとどっちに合った曖昧検索
	public List<Userinfo> findAllByAnything(Integer id,String userName){
		return userinfoRepository.findAllByAnything(id,userName);
	}

	//id曖昧検索
	public List<Userinfo> findAllById(Integer id){
		return userinfoRepository.findAllById(id);
	}
	//userNameで曖昧検索
	public List<Userinfo> findAllByUserName(String userName){
		return userinfoRepository.findAllByUserName(userName);
	}

	//ニックネームで曖昧検索
	public List<Userinfo> findAllByUserNickName(String userNickname) {
		return userinfoRepository.findAllByUserNickName(userNickname);
	}

	//telで曖昧検索
	public List<Userinfo> findAllByTel(String tel) {
		return userinfoRepository.findAllByTel(tel);
	}

	//addressで曖昧検索
	public List<Userinfo> findAllByAddress(String address) {
		return userinfoRepository.findAllByAddress(address);
	}

	//emailで曖昧検索
	public List<Userinfo> findAllByEmail(String email) {
		return userinfoRepository.findAllByEmail(email);
	}


}
