package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Control;

@SpringBootTest
class ControlServiceTest {

	@Autowired
	private ControlService controlService;

	@Test
	void testFindAllControls() {
		List<Control> controlList = controlService.findAllControls();
		assertEquals(0, controlList.size());
	}

	@Test
	void testSaveControl() {
		Control control = new Control();
		control.setCharacterName("管理者");
		control.setControlName("zhangsan");
		controlService.saveControl(control);
		List<Control> controlList = controlService.findAllControls();
		assertEquals(1, controlList.size());
	}

	@Test
	void testDeleteControlbyControlId() {
		controlService.deleteControlbyControlId(2);
		List<Control> controlList = controlService.findAllControls();
		assertEquals(0, controlList.size());
	}

	@Test
	void testFindControlByControlId() {
		Control control = controlService.findControlByControlId(3);
		assertEquals(3, control.getId());
	}

	@Test
	void testUpdateCotrolByid() {
		controlService.updateCotrolByid(3, "", '男', "");
		Control control = controlService.findControlByControlId(3);
		assertEquals('男', control.getSex());
	}
}
