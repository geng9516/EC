package com.example.demo.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Cart;
import com.example.demo.entity.ControlLogin;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.UserLogin;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.CartService;
import com.example.demo.service.ControlLoginService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserLoginService;
import com.example.demo.service.UserinfoService;
import com.example.demo.util.RandomOrderNumber;

/**
 * @author geng9516
 *8080/index ログインページ
 */
@Controller
public class LoginController {

	@Autowired
	private ControlLoginService controlLoginService;
	@Autowired
	private UserinfoService userinfoService;
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private ProductService productService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;

	@RequestMapping("/index")
	public ModelAndView ec() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	/**
	 * @param model
	 * @param loginId ログインID
	 * @param password パスワード
	 * @return ログインページへ
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(
			@RequestParam(name = "userid") String loginId,
			@RequestParam(name = "password") String password) {
		ModelAndView mav = new ModelAndView();
		//ログインユーザー存在するかを判断
		Boolean flog = controlLoginService.findExistLoginUser(loginId, password);
		if ("root".equals(loginId) && flog) {
			mav.setViewName("menu");
		}
		/*else if (flog) {
			//管理者ページへデータ送信
			mav = manegers(loginId, password);
			//IDとパスに合った管理者を返す
			ControlLogin controlLogin = controlLoginService.findLoginUser(loginId, password);
			//管理者のログイン回数と最後のログイン時間を記録
			int i = controlLogin.getControl().getLoginTimes();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			controlLogin.getControl().setLastLogin(timestamp);
			controlLogin.getControl().setLoginTimes(i + 1);
			controlLoginService.saveControlLogin(controlLogin);
			mav.setViewName("managers");
			}*/
		else if (userLoginService.findUserLoginById(loginId)) {
			//個人ページのデータ送信
			UserLogin userLogin = userLoginService.findUserByLoginId(loginId, password);
			if (userLogin != null) {
				//ユーザー存在するし、ステータスが使用中
				String status = userLogin.getUserinfo().getStatus();
				if ("使用中".equals(status)) {
					mav = personalInterfacecustom(loginId, password);
					mav.setViewName("personalInterfacecustom");
				}else {
					mav.addObject("error", "ユーザーいません");
					mav.setViewName("error");
				}
			} else {
				mav.addObject("error", "ユーザーいません");
				mav.setViewName("error");
			}
		} else {
			mav.addObject("error", "IDかパスワードが間違っています");
			mav.setViewName("error");
		}
		return mav;
	}

	@RequestMapping("/searchItem")
	public String searchItem(Model model,
			@RequestParam(name = "loginId") String userLoginId,
			@RequestParam(name = "text") String text) {
		//LoginIdでuserLoginを取得
		UserLogin userLogin = userLoginService.findUserLoginByLoginId(userLoginId);
		if(!text.isEmpty()) {
			List<Product> productList = productService.findAllProductByAnything(text);
			model.addAttribute("userLogin", userLogin);
			model.addAttribute("productList", productList);
		}else {
			List<Product> productList = productService.findProductsByStatus("出品中");
			model.addAttribute("userLogin", userLogin);
			model.addAttribute("productList", productList);
		}
		return "personalInterfacecustom";
	}

	/**管理者ページへ戻る（なし）
	 * @return
	 */
	@RequestMapping("/backManagers")
	public ModelAndView backManagers(
			@RequestParam(name = "loginId") String loginId,
			@RequestParam(name = "password") String password) {
		ModelAndView mav = new ModelAndView();
		//loginIdとpasswordで管理者情報を取得
		mav = manegers(loginId, password);
		mav.setViewName("managers");
		return mav;
	}

	/**管理者ページへデータ送信
	 * @param loginId
	 * @param password
	 * @return
	 */
	public ModelAndView manegers(String loginId, String password) {
		ModelAndView mav = new ModelAndView();
		ControlLogin controlLogin = controlLoginService.findLoginUser(loginId, password);
		//管理者ページにデータ
		mav.addObject("controlLogin", controlLogin);
		return mav;
	}

	/**個人ページのデータ送信
	 * @param model
	 * @param loginId
	 * @param password
	 * @return
	 */
	public ModelAndView personalInterfacecustom(String userLoginId, String password) {
		ModelAndView mav = new ModelAndView();
		UserLogin userLogin = userLoginService.findUserByLoginId(userLoginId, password);
		List<Product> productList = productService.findProductsByStatus("出品中");
		mav.addObject("userLogin", userLogin);
		mav.addObject("password", password);
		mav.addObject("productList", productList);
		return mav;
	}

	/**カートに入れる
	 * @param productId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/basketIn")
	public String basketIn(
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId) {
//		ModelAndView mav = new ModelAndView();
		Cart cart = new Cart();
		Product product = productService.findProductById(productId);
		//商品のアクセス数を加算する
		Integer i = product.getAccessNumber();
		product.setAccessNumber(Integer.valueOf(i) + 1);
		productService.save(product);
		List<Cart> cartList = cartService.fingCartById(userId);
		//同じユーザーのカート内商品数が１以上あれば
		if (cartList.size() > 0) {
			cart = cartService.findCartByPid(productId);
			if (cart != null) {
				cart.setNumber(cart.getNumber() + 1);
				cartService.save(cart);
			} else {
				//				Product product2 = productService.findProductById(productId);
				Cart cart2 = new Cart();
				cart2.setpId(product.getId());
				cart2.setuId(userId);
				cart2.setProductType(product.getProductType());
				cart2.setProductIntro(product.getProductIntro());
				cart2.setSales(product.getSales());
				cart2.setNumber(1);
				cartService.save(cart2);
			}
//			cartList = cartService.fingCartById(userId);
//			mav.addObject("cartList", cartList);
//			mav.addObject("userId", userId);
//			mav.setViewName("basket");
//			return mav;
			//なければ、追加し表示
		} else {
			cart.setpId(product.getId());
			cart.setuId(userId);
			cart.setProductType(product.getProductType());
			cart.setProductIntro(product.getProductIntro());
			cart.setSales(product.getSales());
			cart.setNumber(1);
			cartService.save(cart);
			cartList = cartService.fingCartById(userId);
//			mav.addObject("cartList", cartList);
//			mav.addObject("userId", userId);
//			mav.setViewName("basket");
//			return mav;
		}
		return "redirect:/backPerson?userId="+userId;
	}

	@RequestMapping("/delectCartItem")
	public String delectCartItem(Model model,
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId) {
		//個人のカート内容の特定商品を削除
		cartService.deleteByProductIdAndUserId(productId,userId);
		return "redirect:/intoBasket?userId="+userId;
	}

	/**カートをクリックで一覧を表示
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/intoBasket")
	public String intoBasket(Model model,
			@RequestParam(name = "userId") Integer userId) {
		List<Cart> cartList = cartService.fingCartById(userId);
		model.addAttribute("cartList", cartList);
		model.addAttribute("userId", userId);
		return "basket";
	}

	/**個人ページへ
	 * @param userId
	 * @return
	 */
	@RequestMapping("/backPerson")
	public ModelAndView backPerson(
			@RequestParam(name = "userId") Integer userId) {
		ModelAndView mav = new ModelAndView();
		Userinfo userinfo = userinfoService.findUser(userId);
		mav = personalInterfacecustom(userinfo.getUserLogin().getLoginId(), userinfo.getUserLogin().getPass());
		mav.setViewName("personalInterfacecustom");
		return mav;
	}

	/**購入手続きへ
	 * @param model
	 * @param productId
	 * @param userId
	 * @param sales
	 * @return
	 */
	@RequestMapping("/buyProcess")
	public String buyProcess(Model model,
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId,
			@RequestParam(name = "sales") Double sales,
			@RequestParam(name = "number") Integer number
			) {
		//userIdで特定の商品を取得
		Cart cart = cartService.findByProductIdAndUserId(productId,userId);
		model.addAttribute("cart", cart);
		/*List<Cart> cartList = cartService.fingCartById(userId);
		for (Cart cart : cartList) {
		}*/
		return "buyProduct";
	}

	/**購入ボタン
	 * @param model
	 * @param productId
	 * @param userId
	 * @param sales
	 * @return
	 */
	@RequestMapping("/buyIt")
	public String buyIt(Model model,
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId,
			@RequestParam(name = "sales") Double sales,
			@RequestParam(name = "buyWay") String buyWay) {
		Userinfo userinfo = userinfoService.findUser(userId);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowTimeStr = sdf.format(timestamp);
		RandomOrderNumber ron = new RandomOrderNumber();
		String randomOrderNumber = ron.random();
		//获取
		Product product = productService.findProductById(productId);
		Order order = new Order();
		order.setOrderId(nowTimeStr + randomOrderNumber);
		order.setStatusByOrder("注文処理中");
		order.setStatusByBuy("支払確認中");
		order.setTotal(sales);
		order.setPayment(sales);
		order.setAddress(userinfo.getAddress());
		order.setNumberByOrder(1);
		order.setDateCreated(timestamp);
		order.setPayTime(timestamp);
		order.setUser(userinfo);
		order.setBuyWay(buyWay);
		//多对多关联
		order.getProduct().add(product);
		product.getOrder().add(order);
		//把关系保存
		orderService.save(order);
		productService.save(product);
		//商品数をマイナスする
		product.setStock(product.getStock() - 1);
		productService.save(product);
		//カートの支払済商品をマイナス
		cartService.deleteByProductId(productId);
		List<Cart> cartList = cartService.fingCartById(Integer.valueOf(userId));
		model.addAttribute("cartList", cartList);
		model.addAttribute("userId", userId);
		return "basket";
	}

	@RequestMapping("/backBasket")
	public String backBasket(
			@RequestParam(name = "userId") Integer userId) {
		///intoBasket重定向
		return "redirect:/intoBasket?userId=" + userId;
	}

	//menuに戻る
	@RequestMapping("/menu")
	public String menu(Model model) {
		return "menu";
	}

}
