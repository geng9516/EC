package com.example.demo.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.UserLogin;
import com.example.demo.entity.Userinfo;
import com.example.demo.service.CartService;
import com.example.demo.service.ControlLoginService;
import com.example.demo.service.ControlService;
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
	private ControlService controlService;
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
	 * @param password　パスワード
	 * @return　ログインページへ
	 */
	@RequestMapping("/login")
	public ModelAndView login(
			@RequestParam(name = "userid") String loginId,
			@RequestParam(name = "password") String password) {
		ModelAndView mav = new ModelAndView();
		//ログインユーザーを取得
		Boolean flog = controlLoginService.findControlUser(loginId, password);
		if ("root".equals(loginId) && flog) {
			/*	int i = control.getLoginTimes();
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				control.setLastLogin(timestamp);
				control.setLoginTimes(i + 1);
				controlService.saveControl(control);*/
			mav.setViewName("menu");
		} else if (userLoginService.findUserLoginById(loginId)) {
			mav = personalInterfacecustom(loginId, password);
			mav.setViewName("personalInterfacecustom");
		} else {
			mav.addObject("error", "IDかパスワードが間違っています");
			mav.setViewName("error");
		}
		return mav;
	}

	/**個人ページのデータ送信
	 * @param model
	 * @param loginId
	 * @param password
	 * @return
	 */
	public ModelAndView personalInterfacecustom(String loginId, String password) {
		ModelAndView mav = new ModelAndView();
		UserLogin userLogin = userLoginService.findUserByLoginId(loginId, password);
		List<Product> productList = productService.findProductsByStatus("出品中");
		mav.addObject("userLogin", userLogin);
		mav.addObject("password", password);
		mav.addObject("productList", productList);
		return mav;
	}

	//カートに入れる
	/**
	 * @param productId
	 * @param userId
	 * @return
	 */
	@RequestMapping("/basketIn")
	public ModelAndView basketIn(
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId) {
		ModelAndView mav = new ModelAndView();
		Product product = productService.findProductById(productId);
		//商品のアクセス数を加算する
		Integer i = product.getAccessNumber();
		product.setAccessNumber(Integer.valueOf(i) + 1);
		productService.save(product);
		Boolean flog = cartService.findCartByPid(productId);
		//カート内に商品あれば、表示
		if (flog) {
			List<Cart> cartList = cartService.fingCartById(userId);
			mav.addObject("cartList", cartList);
			mav.addObject("userId", userId);
			mav.setViewName("basket");
			return mav;
			//なければ、追加し表示
		} else {
			Cart cart = new Cart();
			cart.setpId(product.getId());
			cart.setuId(userId);
			cart.setProductType(product.getProductType());
			cart.setProductIntro(product.getProductIntro());
			cart.setSales(product.getSales());
			cartService.save(cart);
			List<Cart> cartList = cartService.fingCartById(userId);
			mav.addObject("cartList", cartList);
			mav.addObject("userId", userId);
			mav.setViewName("basket");
			return mav;
		}
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

	@RequestMapping("/backPerson")
	public ModelAndView backPerson(
			@RequestParam(name = "userId") Integer userId
	//			@RequestParam(name = "productId") String productId
	) {
		ModelAndView mav = new ModelAndView();
		Userinfo userinfo = userinfoService.findUser(userId);
		mav = personalInterfacecustom(userinfo.getUserLogin().getLoginId(), userinfo.getUserLogin().getPass());
		mav.setViewName("personalInterfacecustom");
		return mav;
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
			@RequestParam(name = "sales") Double sales) {
		Userinfo userinfo = userinfoService.findUser(userId);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Set<Order> setOrder = new HashSet<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowTimeStr = sdf.format(timestamp);
		RandomOrderNumber ron = new RandomOrderNumber();
		String randomOrderNumber = ron.random();
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
		//		//orderとユーザーを関連つける
		//		setOrder.add(order);
		//		userinfo.setOrder(setOrder);
		//		userinfoService.saveUserinfo(userinfo);
		orderService.save(order);
		//商品数をマイナスする
		Product product = productService.findProductById(productId);
		product.setStock(product.getStock() - 1);
		productService.save(product);
		//カートの支払済商品をマイナス
		cartService.deleteByProductId(productId);
		List<Cart> cartList = cartService.fingCartById(userId);
		model.addAttribute("cartList", cartList);
		model.addAttribute("userId", userId);
		return "basket";
	}

	//menuに戻る
	@RequestMapping("/menu")
	public String menu(Model model) {
		return "menu";
	}

}
