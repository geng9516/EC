package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.UserLogin;
import com.example.demo.entity.Userinfo;

@SpringBootTest
@Transactional
class UserinfoServiceTest {

	@Autowired
	private UserinfoService userinfoService;

	@Autowired
	private UserLoginService userLoginService;

	//ユーザーListを取得
	@Test
	void testFindUserinfos() {
		List<Userinfo> userinfoList = userinfoService.findUserinfos();
		assertEquals(1, userinfoList.size());
	}

	//シーケンスでuserを取得
	@Test
	void testFindUser() {
		Userinfo userinfo = userinfoService.findUser(3);
		assertEquals("zhangsan", userinfo.getUserName());
	}

	//userを保存する
	@Test
	void testSaveUserinfo() {
		Userinfo userinfo = new Userinfo();
		UserLogin userLogin = new UserLogin();
		userinfo.setUserName("zhangsan");
		userinfo.setUserNickname("zhangsan");
		userLogin.setLoginId("zhangsan");
		userLogin.setPass("0000");
		userinfo.setUserLogin(userLogin);
		userLogin.setUserinfo(userinfo);
		userinfoService.saveUserinfo(userinfo);
		userLoginService.saveUserLogin(userLogin);
		List<Userinfo> userinfoList = userinfoService.findUserinfos();
		assertEquals(1, userinfoList.size());
		List<UserLogin> userLoginList = userLoginService.findUser_login();
		assertEquals(1, userLoginList.size());
	}

	//削除
	@Test
	void testDeleteUser() {
		Userinfo userinfo = userinfoService.findUser(3);
		userinfoService.deleteUser(userinfo);
		List<Userinfo> userinfoList = userinfoService.findUserinfos();
		assertEquals(0, userinfoList.size());
		List<UserLogin> userLoginList = userLoginService.findUser_login();
		assertEquals(0, userLoginList.size());
	}

	//idとuserNameでとどっちに合った曖昧検索
	@Test
	void testFindAllByAnything() {
		List<Userinfo> userinfoList = userinfoService.findAllByAnything(3, "zhangsan");
		assertEquals(1, userinfoList.size());
	}

	//id曖昧検索
	@Test
	void testFindAllById() {
		List<Userinfo> userinfoList =userinfoService.findAllById(3);
		assertEquals(1, userinfoList.size());
	}

	//userNameで曖昧検索
	@Test
	void testFindAllByUserName() {
		List<Userinfo> userinfoList = userinfoService.findAllByUserName("zhang");
		assertEquals(1,userinfoList.size());
	}

	//ニックネームで曖昧検索
	@Test
	void testFindAllByUserNickName() {
		List<Userinfo> userinfoList = userinfoService.findAllByUserNickName("san");
		assertEquals(1,userinfoList.size());
	}

	//telで曖昧検索
	@Test
	void testFindAllByTel() {
		List<Userinfo> userinfoList = userinfoService.findAllByTel("8822");
		assertEquals(1,userinfoList.size());
	}

	//addressで曖昧検索
	@Test
	void testFindAllByAddress() {
		List<Userinfo> userinfoList = userinfoService.findAllByAddress("市川");
		assertEquals(1,userinfoList.size());
	}

	//emailで曖昧検索
	@Test
	void testFindAllByEmail() {
		List<Userinfo> userinfoList = userinfoService.findAllByEmail("c");
		assertEquals(1,userinfoList.size());
	}

}
