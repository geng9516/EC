package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.ControlLogin;
@Transactional
@SpringBootTest
class ControlLoginServiceTest {
	@Autowired
	private ControlLoginService controlLoginService;

	@Test
	void testSaveControlLogin() {
		ControlLogin controlLogin = new ControlLogin();
		controlLogin.setLoginId("ffff");
		controlLogin.setPass("0000");
		controlLoginService.saveControlLogin(controlLogin);
		List<ControlLogin> controlLoginList = controlLoginService.findAllControlLogin();
		assertEquals(4, controlLoginList.size());
	}

	@Test
	void testFindExistLoginUser() {
		Boolean flog = controlLoginService.findExistLoginUser("aaa", "0000");
		assertEquals(true, flog);
	}

	@Test
	void testFindLoginUser() {
		ControlLogin controlLogin = controlLoginService.findLoginUser("aaa", "0000");
		assertNotNull(controlLogin);
	}

	@Test
	void testFindControlLoginById() {
		ControlLogin controlLogin = controlLoginService.findControlLoginById(2);
		assertNotNull(controlLogin);
	}

	@Test
	void testDeleteControlLogin() {
		ControlLogin controlLogin = controlLoginService.findControlLoginById(3);
		controlLoginService.deleteControlLogin(controlLogin);
		List<ControlLogin> controlLoginList = controlLoginService.findAllControlLogin();
		assertEquals(5, controlLoginList.size());
	}

	@Test
	void testUpdateCotrolByid() {
		controlLoginService.updateCotrolByid(4, "1111");
		ControlLogin controlLogin = controlLoginService.findControlLoginById(4);
		assertEquals("1111", controlLogin.getPass());
	}

}
