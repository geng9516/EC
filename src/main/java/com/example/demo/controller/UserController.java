package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entity.UserLogin;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.UserLoginService;
import com.example.demo.service.UserinfoService;
import com.example.demo.util.RandomOrderNumber;

@Controller
public class UserController {

	/*
	ユーザー
	*/

	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UserLoginService userLoginService;

	//一覧
	@RequestMapping("/userAll")
	public String userAll(Model model) {
		List<Userinfo> list = userinfoService.findUserinfos();

		model.addAttribute("userinfoList", list);
		return "userAll";
	}

	//追加
	@Value("${ICON_PATH}")
	private String path;
	@Value("${PICTURE_URL}")
	private String pictureUrl;

	@RequestMapping("/userAdd")
	public String userAdd(Model model, String str) {
		model.addAttribute("str", str);
		return "userAdd";
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public RedirectView saveUser(
			@RequestParam(name = "str") String str,
			@RequestParam(name = "photo") MultipartFile photo,
			@RequestParam(name = "username") String userName,
			@RequestParam(name = "nickname") String nickName,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "sex") Character sex,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "birth") String birth,
			@RequestParam(name = "password") String password) {
		File newName = null;
		System.out.println(photo);
		if (!photo.isEmpty()) {
			try {
				RandomOrderNumber ron = new RandomOrderNumber();
				String name = ron.random();
				File oldName = new File(photo.getOriginalFilename());
				System.out.println(oldName);
				newName = new File(name + ".jpg");
				oldName.renameTo(newName);
				byte[] bytes = photo.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path + newName)));
				stream.write(bytes);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		RedirectView redirectTarget = new RedirectView();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Userinfo userinfo = new Userinfo();
		UserLogin userLogin = new UserLogin();
		userinfo.setPhoto(pictureUrl + newName.toString());
		userinfo.setUserName(userName);
		userinfo.setUserNickname(nickName);
		userinfo.setTel(tel);
		userinfo.setEmail(email);
		userinfo.setSex(sex);
		userinfo.setAddress(address);
		userinfo.setStatus("普通");
		userinfo.setBirth(Date.valueOf(birth));
		userinfo.setDateCreated(timestamp);
		userLogin.setLoginId(nickName);
		userLogin.setPass(password);
		//userinfoとuserLoginを関連つける
		userinfo.setUserLogin(userLogin);
		userLogin.setUserinfo(userinfo);
		userinfoService.saveUserinfo(userinfo);
		userLoginService.saveUserLogin(userLogin);
		if ("userAll".equals(str)) {
			redirectTarget.setUrl("userAll");
		} else if ("login".equals(str)) {
			redirectTarget.setUrl("index");
		}
		return redirectTarget;
	}

	//
	/*/削除
	 * 默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除.
	 * 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
	 * cascade = CascadeType.REMOVE
	 */
	@RequestMapping("deleteUser")
	public RedirectView deleteUser(Integer userId) {
		Userinfo userinfo = userinfoService.findUser(userId);
		userinfoService.deleteUser(userinfo);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("userAll");
		return redirectTarget;
	}

	//編集
	@RequestMapping("/userEdit")
	public String userEdit(Model model,
			@RequestParam(name = "userId") Integer userId) {
		Userinfo userinfo = userinfoService.findUser(userId);
		model.addAttribute("userinfo", userinfo);
		return "userEdit";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public RedirectView editUser(
			@RequestParam(name = "userId") Integer userId,
			@RequestParam(name = "photo") MultipartFile photo,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "birth") String birth,
			@RequestParam(name = "password") String password) {
		Userinfo userinfo = userinfoService.findUser(userId);

		File newName = null;
		if (!photo.isEmpty()) {
			try {
				File oldName = new File(photo.getOriginalFilename());
				newName = new File(tel + ".jpg");
				oldName.renameTo(newName);
				byte[] bytes = photo.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(path + newName)));
				stream.write(bytes);
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				userinfo.setPhoto(pictureUrl + newName.toString());
			}
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userinfo.setTel(tel);
		userinfo.setEmail(email);
		userinfo.setAddress(address);
		userinfo.setBirth(Date.valueOf(birth));
		userinfo.setDateModified(timestamp);
		userinfo.getUserLogin().setPass(password);
		//		userinfo.setUserLogin(userLogin);
		//userinfoとuserLoginを関連つける
		userinfoService.saveUserinfo(userinfo);
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("userAll");
		return redirectTarget;
	}

	@RequestMapping("/searchUser")
	public RedirectView searchUser(
			@RequestParam(name = "userId") Integer id,
			@RequestParam(name = "userName") Integer userName,
			@RequestParam(name = "nickname") Integer userNickname,
			@RequestParam(name = "tel") Integer tel,
			@RequestParam(name = "email") Integer email,
			@RequestParam(name = "sex") Integer sex,
			@RequestParam(name = "status") Integer status,
			@RequestParam(name = "address") Integer address,
			@RequestParam(name = "birth") Integer birth) {
		System.out.println(id+"======================");
		RedirectView redirectTarget = new RedirectView();
		List<Object> list = new ArrayList<>();
		list.add(id);
		list.add(userName);
		list.add(userNickname);
		list.add(tel);
		list.add(email);
		list.add(sex);
		list.add(status);
		list.add(address);
		list.add(birth);
		for(Object o:list) {
			if(o != null) {
				System.out.println(o);
			}
		}
		redirectTarget.setUrl("userAll");
		return redirectTarget;
	}

	@RequestMapping("/backUserAll")
	public RedirectView backUserAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("userAll");
		return redirectTarget;
	}

}
