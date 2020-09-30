package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShopController {
	@RequestMapping("/")
	public ModelAndView ec() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("menu");
		return mav;
	}

	//menuに戻る
	@RequestMapping("/menu")
	public String menu(Model model) {
		return "menu";
	}

	/*
		管理者
	 */
	@RequestMapping("/characterAll")
	public String characterAll(Model model) {
		return "characterAll";
	}

	@RequestMapping("/characterAdd")
	public String characterAdd(Model model) {
		return "characterAdd";
	}

	@RequestMapping("/characterEdit")
	public String characterEdit(Model model) {
		return "characterEdit";
	}

	@RequestMapping("/backCharacterAll")
	public String backCharacterAll(Model model) {
		return "characterAll";
	}

	/*
	権限
	*/
	@RequestMapping("/controlAll")
	public String controlAll(Model model) {
		return "controlAll";
	}

	@RequestMapping("/controlAdd")
	public String controlAdd(Model model) {
		return "controlAdd";
	}

	@RequestMapping("/controlEdit")
	public String controlEdit(Model model) {
		return "controlEdit";
	}

	@RequestMapping("/backControlAll")
	public String backControlAll(Model model) {
		return "controlAll";
	}

	/*
	ユーザー
	*/
	@RequestMapping("/userAll")
	public String userAll(Model model) {
		return "userAll";
	}

	@RequestMapping("/userAdd")
	public String userAdd(Model model) {
		return "userAdd";
	}

	@RequestMapping("/userEdit")
	public String userEdit(Model model) {
		return "userEdit";
	}

	@RequestMapping("/backUserAll")
	public String backUserAll(Model model) {
		return "userAll";
	}

	/*
	商品
	*/
	@RequestMapping("/productAll")
	public String productAll(Model model) {
		return "productAll";
	}

	@RequestMapping("/productAdd")
	public String productAdd(Model model) {
		return "productAdd";
	}

	@RequestMapping("/productEdit")
	public String productEdit(Model model) {
		return "productEdit";
	}

	@RequestMapping("/backProductAll")
	public String backProductAll(Model model) {
		return "productAll";
	}

	/*
	注文
	*/
	@RequestMapping("/orderAll")
	public String orderAll(Model model) {
		return "orderAll";
	}

	@RequestMapping("/orderEdit")
	public String orderEdit(Model model) {
		return "orderAll";
	}

	@RequestMapping("/backOrderAll")
	public String backOrderAll(Model model) {
		return "orderAll";
	}

}
