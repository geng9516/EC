package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Control;
import com.example.demo.entity.ControlLogin;
import com.example.demo.service.ControlLoginService;
import com.example.demo.service.ControlService;

@Controller
public class ControlController {

	/*
	管理者一覧
	*/

	@Autowired
	private ControlService controlService;
	@Autowired
	private ControlLoginService controlLoginService;

	//一覧
	@RequestMapping("/controlAll")
	public String controlAll(Model model) {
		List<Control> list = new ArrayList<>();
		list = controlService.findAllControls();
		model.addAttribute("controlList", list);
		return "controlAll";
	}

	//追加
	@RequestMapping("/controlAdd")
	public ModelAndView controlAdd() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("controlAdd");
		return mav;
	}

	@RequestMapping(value = "/saveControl")
	public RedirectView saveControl(
			@RequestParam(name = "controlname") String controlName,
			@RequestParam(name = "status") String status,
			@RequestParam(name = "charactername") String characterName,
			@RequestParam(name = "sex") Character sex,
			@RequestParam(name = "tel") String tel) {
		RedirectView redirectTarget = new RedirectView();
		Control control = new Control();
		ControlLogin controlLogin = new ControlLogin();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		control.setControl_name(controlName);
		control.setStatusbycontrol(status);
		control.setCharacter_name(characterName);
		control.setSex(sex);
		control.setTel(tel);
		control.setDate_created(timestamp);
		control.setControlLogin(controlLogin);
		controlLogin.setLogin_id(controlName);
		controlLogin.setControl(control);
		controlLogin.setPass(controlName);
		controlService.saveControl(control);
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}

	/* 削除
	 * (级联关系的两张表要进行删除的话，
	 * 1，要先把级联关系解除======control.setControlLogin(null);
	 * 2，然后把字表先进行删除====controlLoginService.deleteControlLogin(controlLogin);
	 * 3，最后把父表删除=========controlService.deleteControlbyControlName(controlId);)
	 *
	 *参考：userinfoの削除方法
	 */
	@RequestMapping("/delteControl")
	public RedirectView delteControl(
			@RequestParam(name = "control_id") Integer controlId,
			@RequestParam(name = "cid") Integer cid) {
		RedirectView redirectTarget = new RedirectView();
		ControlLogin controlLogin = new ControlLogin();
		controlLogin = controlLoginService.findControlLoginByControlName(cid);
		controlLoginService.deleteControlLogin(controlLogin);
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}

	//編集
	@RequestMapping("/controlEdit")
	public ModelAndView controlEdit(
			@RequestParam(name = "control_id") Integer controlId) {
		ModelAndView mav = new ModelAndView();
		Control control = controlService.findControlByControlId(controlId);
		System.out.println(control);

		mav.addObject("control", control);
		mav.setViewName("controlEdit");
		return mav;
	}

	@RequestMapping("/editControl")
	public RedirectView editControl(
			@RequestParam(name = "control_id") Integer controlId,
			@RequestParam(name = "controlLogin_id") Integer controlLoginId,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "sex") Character sex,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "status") String status) {
		RedirectView redirectTarget = new RedirectView();
		controlService.updateCotrolByid(controlId, status, sex, tel);
		controlLoginService.updateCotrolByid(controlLoginId, password);
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}

	//戻る
	@RequestMapping("/backControlAll")
	public RedirectView backControlAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}
}
