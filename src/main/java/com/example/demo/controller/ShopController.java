package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.UserLogin;
import com.example.demo.service.ControlLoginService;
import com.example.demo.service.ControlService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserLoginService;
import com.example.demo.service.UserinfoService;

@Controller
public class ShopController {

	@Autowired
	private ControlLoginService controlLoginService;
	@Autowired
	private ControlService controlService;

	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UserLoginService userLoginService;

	@Autowired
	private ProductService ProductService;

	@RequestMapping("/index")
	public ModelAndView ec() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("/login")
	public String login(
			Model model,
			@RequestParam(name = "userid") String loginId,
			@RequestParam(name = "password") String password) {
		System.out.println(loginId  +  password);
		String targetUrl = null;
		String name = controlService.fingByControlName(loginId);
		if ("管理者".equals(name)) {
			targetUrl = "menu";
		} else {
			UserLogin userLogin = userLoginService.findUserByLoginId(loginId, password);
			System.out.println(userLogin);
			targetUrl="personalInterfacecustom";
			model.addAttribute("userlogin",userLogin);
			model.addAttribute("date","hello");
		}
		return targetUrl;
	}

	//menuに戻る
	@RequestMapping("/menu")
	public String menu(Model model) {
		return "menu";
	}

}
