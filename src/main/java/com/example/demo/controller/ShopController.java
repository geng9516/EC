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

import com.example.demo.entity.Characters;
import com.example.demo.service.CharacterService;

@Controller
public class ShopController {

	@Autowired
	private CharacterService characterService;

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

	//管理者画面へ
	@RequestMapping("/characterAll")
	public String characterAll(Model model) {
		List<Characters> list = new ArrayList<>();
		list = characterService.findAllCharacter();
		model.addAttribute("characterList", list);
		return "characterAll";
	}

	//跳转到url
	@RequestMapping("/characterDelete")
	public RedirectView CharacterDelete(
			@RequestParam(name="character_name")String character_name
			) {
		RedirectView redirectTarget = new RedirectView();
		characterService.deleteCharacterById(character_name);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	@RequestMapping("/characterAdd")
	public String characterAdd(Model model) {
		return "characterAdd";
	}

	@RequestMapping("/saveCharacter")
	public RedirectView saveCharacter(
			@RequestParam(name="charactername")String charactername,
			@RequestParam(name="status")String status
			) {
		Characters character = new Characters();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		character.setCharacter_name(charactername);
		character.setStatusbycharacter(status);
		character.setDate_created(timestamp);
		RedirectView redirectTarget = new RedirectView();
		characterService.saveCharacter(character);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	@RequestMapping("/characterEdit")
	public ModelAndView characterEdit(
			@RequestParam(name="character_name")String character_name
			) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", character_name);
		mav.setViewName("characterEdit");
		return mav;
	}

	@RequestMapping("/editChatacter")
	public RedirectView editChatacter(
			@RequestParam(name="status")String status
			) {
		RedirectView redirectTarget = new RedirectView();
		Characters character = new Characters();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		character.setStatusbycharacter(status);
		character.setDate_modified(timestamp);
		characterService.editChatacter(character);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}



	@RequestMapping("/backCharacterAll")
	public RedirectView backCharacterAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
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
