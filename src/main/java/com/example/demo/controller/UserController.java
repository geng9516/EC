package com.example.demo.controller;

import java.io.File;
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

import com.example.demo.entity.UserLogin;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.UserLoginService;
import com.example.demo.service.UserinfoService;
import com.example.demo.util.PhotoAdd;

@Controller
public class UserController {

	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UserLoginService userLoginService;

	/**商品一覧へ
	 * @param model
	 * @param str 新規追加画面から一覧画面へ戻るときに使う判断（引数：menu）
	 * @return
	 */
	@RequestMapping("/userAll")
	public String userAll(Model model,
			@RequestParam(name = "str") String str) {
		List<Userinfo> list = userinfoService.findUserinfos();
		model.addAttribute("str", str);
		model.addAttribute("userinfoList", list);
		return "userAll";
	}

	//写真を保存用の相対path
	@Value("${ICON_PATH}")
	private String path;
	//写真を読み取るときのpath
	@Value("${PICTURE_URL}")
	private String pictureUrl;

	/**user追加画面へ
	 * @param model
	 * @param str
	 * @return
	 */
	@RequestMapping("/userAdd")
	public String userAdd(Model model,
			@RequestParam(name = "str") String str) {
		model.addAttribute("str", str);
		return "userAdd";
	}

	/**追加
	 * @param str
	 * @param photo
	 * @param userName
	 * @param nickName
	 * @param tel
	 * @param email
	 * @param sex
	 * @param address
	 * @param birth
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(
			Model model,
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
		Userinfo userinfo = new Userinfo();
		UserLogin userLogin = new UserLogin();
		File newName = PhotoAdd.AddPhoto(photo, path);
		userinfo.setPhoto(pictureUrl + newName.toString());
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userinfo.setUserName(userName);
		userinfo.setUserNickname(nickName);
		userinfo.setTel(tel);
		userinfo.setEmail(email);
		userinfo.setSex(sex);
		userinfo.setAddress(address);
		userinfo.setStatus("使用中");
		userinfo.setBirth(Date.valueOf(birth));
		userinfo.setDateCreated(timestamp);
		userLogin.setLoginId(nickName);
		userLogin.setPass(password);
		//userinfoとuserLoginを関連つける
		userinfo.setUserLogin(userLogin);
		userLogin.setUserinfo(userinfo);
		userinfoService.saveUserinfo(userinfo);
		userLoginService.saveUserLogin(userLogin);
		String s = "";
		if ("menu".equals(str)) {
			s = "redirect:/userAll?str=" + str;
		} else if ("login".equals(str)) {
			s = "redirect:/index";
		}
		return s;
	}

	/**削除
	 * 默认情况下, 若删除 1 的一端, 则会先把关联的 n 的一端的外键置空, 然后进行删除.
	 * 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略.
	 * cascade = CascadeType.REMOVE
	 */
	@RequestMapping("deleteUser")
	public String deleteUser(Model model, Integer userId) {
		Userinfo userinfo = userinfoService.findUser(userId);
		userinfo.setStatus("使用停止");
		userinfoService.saveUserinfo(userinfo);
		return "redirect:/userAll?str=" + "menu";
	}

	/**編集画面へ
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/userEdit")
	public String userEdit(Model model,
			@RequestParam(name = "userId") Integer userId) {
		Userinfo userinfo = userinfoService.findUser(userId);
		model.addAttribute("userinfo", userinfo);
		return "userEdit";
	}

	/**編集
	 * @param userId
	 * @param photo
	 * @param tel
	 * @param email
	 * @param address
	 * @param birth
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(
			@RequestParam(name = "userId") Integer userId,
			@RequestParam(name = "photo") MultipartFile photo,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "address") String address,
			@RequestParam(name = "status") String status,
			@RequestParam(name = "password") String password) {
		Userinfo userinfo = userinfoService.findUser(userId);
		if (!photo.isEmpty()) {
			File newName = PhotoAdd.AddPhoto(photo, path);
			userinfo.setPhoto(pictureUrl + newName.toString());
		}
		//更新時間を追加
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		userinfo.setDateModified(timestamp);

		if (tel != null) {
			userinfo.setTel(tel);
		}
		if (email != null) {
			userinfo.setEmail(email);
		}
		if (address != null) {
			userinfo.setAddress(address);
		}
		if (!password.isEmpty()) {
			userinfo.getUserLogin().setPass(password);
		}
		if (status != null) {
			userinfo.setStatus(status);
		}
		//userinfoとuserLoginを関連つける
		userinfoService.saveUserinfo(userinfo);
		//引数にmenuが必要
		return "redirect:/userAll?str=" + "menu";
	}

	/**検索
	 * @param id
	 * @param userName
	 * @param userNickname
	 * @param tel
	 * @param email
	 * @param sex
	 * @param status
	 * @param address
	 * @param birth
	 * @return
	 */
	@RequestMapping("/searchUser")
	public String searchUser(Model model,
			@RequestParam(name = "userId") Integer id,
			@RequestParam(name = "userName") String userName,
			@RequestParam(name = "nickname") String userNickname,
			@RequestParam(name = "tel") String tel,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "address") String address) {
		List<Userinfo> userinfoList = new ArrayList<>();
		if (id != null) {
			//id曖昧検索
			userinfoList = userinfoService.findAllById(id);
			//idとuserNameでとどっちに合った曖昧検索
			if (id != null && !userName.isEmpty()) {
				userinfoList = userinfoService.findAllByAnything(id, userName);
			}
		} else if (!userName.isEmpty()) {
			//userNameで曖昧検索
			userinfoList = userinfoService.findAllByUserName(userName);
		} else if (!userNickname.isEmpty()) {
			//ニックネームで曖昧検索
			userinfoList = userinfoService.findAllByUserNickName(userNickname);
		} else if (!tel.isEmpty()) {
			//telで曖昧検索
			userinfoList = userinfoService.findAllByTel(tel);
		} else if (!email.isEmpty()) {
			//emailで曖昧検索
			userinfoList = userinfoService.findAllByEmail(email);
		} else if (!address.isEmpty()) {
			//addressで曖昧検索
			userinfoList = userinfoService.findAllByAddress(address);
		} else {
			//全検索
			userinfoList = userinfoService.findUserinfos();
		}
		model.addAttribute("userinfoList", userinfoList);
		model.addAttribute("str","menu");
		return "userAll";
	}

	/**一覧へ戻る
	 * @param meodel
	 * @param str
	 * @return
	 */
	@RequestMapping("/backUserAll")
	public String backUserAll(Model meodel,
			@RequestParam(name = "str") String str) {
		//userAllにstrのパラメーターが必須
		return "redirect:/userAll?str=" + str;
	}

}
