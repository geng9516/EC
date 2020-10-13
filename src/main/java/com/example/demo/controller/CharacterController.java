package com.example.demo.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.Characters;
import com.example.demo.service.CharacterService;

@Controller
public class CharacterController {

	/*
	権限一覧
	*/
	@Autowired
	private CharacterService characterService;

	//権限一覧へ
	@RequestMapping("/characterAll")
	public String characterAll(Model model) {
		List<Characters> list = new ArrayList<>();
		list = characterService.findAllCharacter();
		model.addAttribute("characterList", list);
		return "characterAll";
	}

	//削除
	@RequestMapping("/characterDelete")
	public RedirectView CharacterDelete(
			@RequestParam(name = "character_id") Integer characterId) {
		RedirectView redirectTarget = new RedirectView();
		characterService.deleteCharacterByCharacterName(characterId);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	//追加
	@RequestMapping("/characterAdd")
	public String characterAdd(Model model) {
		return "characterAdd";
	}

	@RequestMapping(value = "/saveCharacter", method = RequestMethod.POST)
	public RedirectView saveCharacter(
			@RequestParam(name = "charactername") String charactername,
			@RequestParam(name = "status") String status) {
		RedirectView redirectTarget = new RedirectView();
		Characters character = new Characters();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		character.setCharacter_name(charactername);
		character.setStatusbycharacter(status);
		character.setDate_created(timestamp);
		characterService.saveCharacter(character);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	//編集
	@RequestMapping("/characterEdit")
	public ModelAndView characterEdit(
			@RequestParam(name = "character_id") Integer characterId,
			@RequestParam(name = "character_name") String characterName) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", characterName);
		mav.setViewName("characterEdit");
		return mav;
	}

	@RequestMapping("/editChatacter")
	public RedirectView editChatacter(
			@RequestParam(name = "charactername") String characterName,
			@RequestParam(name = "status") String status) {
		RedirectView redirectTarget = new RedirectView();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		characterService.editChatacter(characterName, status, timestamp);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	//戻る
	@RequestMapping("/backCharacterAll")
	public RedirectView backCharacterAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}
}
