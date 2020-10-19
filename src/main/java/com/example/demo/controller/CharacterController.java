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
			@RequestParam(name = "characterId") Integer characterId) {
		RedirectView redirectTarget = new RedirectView();
		characterService.deleteCharacterByCharacterName(characterId);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	/**追加画面へ
	 * @param model
	 * @return
	 */
	@RequestMapping("/characterAdd")
	public String characterAdd(Model model) {
		return "characterAdd";
	}

	/**権限追加
	 * @param characterName
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/saveCharacter")
	public RedirectView saveCharacter(
			@RequestParam(name = "characterName") String characterName,
			@RequestParam(name = "status") String status) {
		RedirectView redirectTarget = new RedirectView();
		Characters character = new Characters();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		character.setCharacterName(characterName);
		character.setStatusByCharacter(status);
		character.setDateCreated(timestamp);
		characterService.saveCharacter(character);
		redirectTarget.setUrl("characterAll");
		return redirectTarget;
	}

	/**編集画面へ
	 * @param characterId
	 * @param characterName
	 * @return
	 */
	@RequestMapping("/characterEdit")
	public ModelAndView characterEdit(
			@RequestParam(name = "characterId") Integer characterId,
			@RequestParam(name = "characterName") String characterName) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("data", characterName);
		mav.setViewName("characterEdit");
		return mav;
	}

	/**編集
	 * @param characterName
	 * @param status
	 * @return
	 */
	@RequestMapping("/editChatacter")
	public RedirectView editChatacter(
			@RequestParam(name = "characterName") String characterName,
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
