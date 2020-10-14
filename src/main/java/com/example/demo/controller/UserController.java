package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
			}
		}
		RedirectView redirectTarget = new RedirectView();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Set<UserLogin> set = new HashSet<>();
		UserLogin userLogin1 = new UserLogin(null, null, "email", email, password);
		UserLogin userLogin2 = new UserLogin(null, null, "電話", tel, password);
		UserLogin userLogin3 = new UserLogin(null, null, "ニックネーム", nickName, password);
		set.add(userLogin1);
		set.add(userLogin2);
		set.add(userLogin3);
		Userinfo userinfo = new Userinfo();
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
		//userinfoとuserLoginを関連つける
		userinfo.setUserLogin(set);
		userLogin1.setUserinfo(userinfo);
		userLogin2.setUserinfo(userinfo);
		userLogin3.setUserinfo(userinfo);
		//保存操作
		//先に1の方を保存し、次に多の方を保存（こうするとSQL文の発生が少なくなる）
		userinfoService.saveUserinfo(userinfo);
		userLoginService.saveUserLogin(userLogin1);
		userLoginService.saveUserLogin(userLogin2);
		userLoginService.saveUserLogin(userLogin3);
		redirectTarget.setUrl("userAll");
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
		model.addAttribute("userId", userinfo);
		return "userEdit";
	}

	@RequestMapping("/editUser")
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
		userinfo.setSex(sex);
		userinfo.setAddress(address);
		userinfo.setStatus("普通");
		userinfo.setBirth(Date.valueOf(birth));
		userinfo.setDateCreated(timestamp);
		//userinfoとuserLoginを関連つける
		userinfo.setUserLogin(set);
		userLogin1.setUserinfo(userinfo);
		userLogin2.setUserinfo(userinfo);
		userLogin3.setUserinfo(userinfo);
		RedirectView redirectTarget = new RedirectView();
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
