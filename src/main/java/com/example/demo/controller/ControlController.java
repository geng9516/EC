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
import com.example.demo.entity.Control;
import com.example.demo.entity.ControlLogin;
import com.example.demo.service.CharacterService;
import com.example.demo.service.ControlLoginService;
import com.example.demo.service.ControlService;

@Controller
public class ControlController {

	@Autowired
	private ControlService controlService;
	@Autowired
	private ControlLoginService controlLoginService;
	@Autowired
	private CharacterService characterService;

	/**管理者一覧
	 * @param model
	 * @return
	 */
	@RequestMapping("/controlAll")
	public String controlAll(Model model) {
		List<Control> list = new ArrayList<>();
		list = controlService.findAllControls();
		model.addAttribute("controlList", list);
		return "controlAll";
	}

	/**追加画面へ
	 * @return
	 */
	@RequestMapping("/controlAdd")
	public ModelAndView controlAdd() {
		ModelAndView mav = new ModelAndView();
		//すべての権限を取得
		List<Characters> characterList = characterService.findAllCharacter();
		//使用停止の権限を表示しないように
		for (int i = 0;i<characterList.size();i++) {
			if ("使用停止".equals(characterList.get(i).getStatusByCharacter())) {
				characterList.remove(i);
			}
		}
		mav.addObject("characterList", characterList);
		mav.setViewName("controlAdd");
		return mav;
	}

	/**追加
	 * @param controlName
	 * @param status
	 * @param characterName
	 * @param sex
	 * @param tel
	 * @return
	 */
	@RequestMapping(value = "/saveControl")
	public RedirectView saveControl(
			@RequestParam(name = "controlName") String controlName,
			@RequestParam(name = "status") String status,
			@RequestParam(name = "characterName") String characterName,
			@RequestParam(name = "sex") Character sex,
			@RequestParam(name = "tel") String tel) {
		RedirectView redirectTarget = new RedirectView();
		Control control = new Control();
		ControlLogin controlLogin = new ControlLogin();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		control.setControlName(controlName);
		control.setStatusByControl(status);
		control.setCharacterName(characterName);
		control.setSex(sex);
		control.setTel(tel);
		control.setDateCreated(timestamp);
		control.setControlLogin(controlLogin);
		controlLogin.setLoginId(controlName);
		controlLogin.setControl(control);
		controlLogin.setPass("0000");
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
			@RequestParam(name = "controlId") Integer controlId,
			@RequestParam(name = "cId") Integer controlLoginId) {
		RedirectView redirectTarget = new RedirectView();
		Control control = controlService.findControlByControlId(controlId);
		control.setStatusByControl("退職");
		controlService.saveControl(control);
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}

	/**編集画面へ
	 * @param controlId
	 * @return
	 */
	@RequestMapping("/controlEdit")
	public ModelAndView controlEdit(
			@RequestParam(name = "controlId") Integer controlId
			) {
		ModelAndView mav = new ModelAndView();
//		IdでControlユーザーを取得
		Control control = controlService.findControlByControlId(controlId);
		mav.addObject("control", control);
		mav.setViewName("controlEdit");
		return mav;
	}

	/**編集
	 * @param controlId
	 * @param controlLoginId
	 * @param password
	 * @param tel
	 * @param status
	 * @return
	 */
	@RequestMapping("/editControl")
	public RedirectView editControl(
			@RequestParam(name = "controlId") Integer controlId,
			@RequestParam(name = "controlLoginId") Integer controlLoginId,
			@RequestParam(name = "password") String password,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "status") String status) {
		RedirectView redirectTarget = new RedirectView();
		Control control = controlService.findControlByControlId(controlId);
		ControlLogin controlLogin = controlLoginService.findControlLoginById(controlLoginId);
		//(if)更新する内容あれば、更新するなければしない
		if(password != null) {
			controlLogin.setPass(password);
			//更新した内容をDBにupdate
			controlLoginService.saveControlLogin(controlLogin);
		}
		if(tel != null) {
			control.setTel(tel);
		}
		if(status != null) {
			control.setStatusByControl(status);
		}
		//更新した内容をDBにupdate
		controlService.saveControl(control);
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}

	/**管理者一覧へ
	 * @return
	 */
	@RequestMapping("/backControlAll")
	public RedirectView backControlAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("controlAll");
		return redirectTarget;
	}
}
