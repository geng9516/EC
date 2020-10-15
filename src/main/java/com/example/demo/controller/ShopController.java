package com.example.demo.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Control;
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

@Controller
public class ShopController {

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

	@RequestMapping("/login")
	public String login(
			Model model,
			@RequestParam(name = "userid") String loginId,
			@RequestParam(name = "password") String password) {
		String targetUrl = null;
		Control control = controlService.fingByControlName(loginId);
		if (control != null && "root".equals(control.getControl_name())) {
			int i = control.getLogin_times();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			control.setLast_login(timestamp);
			control.setLogin_times(i+1);
			controlService.saveControl(control);
			targetUrl = "menu";
		} else if(userLoginService.findUserLoginById(loginId)){
			UserLogin userLogin = userLoginService.findUserByLoginId(loginId, password);
			List<Product> productList = productService.findProductsByStatus("出品中");
			model.addAttribute("userLogin", userLogin);
			model.addAttribute("productList", productList);
			model.addAttribute("password", password);
			targetUrl = "personalInterfacecustom";
		}else {
			model.addAttribute("error", "IDかパスワードが間違っています");
			targetUrl = "error";
		}
		return targetUrl;
	}

	@RequestMapping("/login2")
	public String login2(
			Model model,
			@RequestParam(name = "userid") String loginId,
			@RequestParam(name = "password") String password) {
		String targetUrl = null;
		Control control = controlService.fingByControlName(loginId);
		if ("root".equals(control.getControl_name())) {

			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			control.setLast_login(timestamp);
			targetUrl = "menu";
		} else {
			UserLogin userLogin = userLoginService.findUserByLoginId(loginId, password);
			List<Product> productList = productService.findProductsByStatus("出品中");
			targetUrl = "personalInterfacecustom";
			model.addAttribute("userLogin", userLogin);
			model.addAttribute("productList", productList);
			model.addAttribute("password", password);
		}
		return targetUrl;
	}

	//カートに入れる
	@RequestMapping("/basketIn")
	public ModelAndView basketIn(
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId
	) {
		ModelAndView mav = new ModelAndView();
		Product product = productService.findProductById(productId);
		Integer i = product.getAccessNumber();
		product.setAccessNumber(Integer.valueOf(i)+1);
		productService.save(product);
		Boolean flog = cartService.findCartByPid(productId);
		System.out.println(flog);
		if (flog) {
			List<Cart> cartList = cartService.fingCartById(userId);
			System.out.println(cartList);
			mav.addObject("cartList", cartList);
			mav.addObject("userId", userId);
			mav.setViewName("basket");
			return mav;
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

	@RequestMapping("/intoBasket")
	public String intoBasket(Model model,
			@RequestParam(name = "userId") Integer userId) {
		List<Cart> cartList = cartService.fingCartById(userId);
		model.addAttribute("cartList", cartList);
		model.addAttribute("userId", userId);
		return "basket";
	}

	@RequestMapping("/buyIt")
	public String buyIt(Model model,
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "userId") Integer userId,
			@RequestParam(name = "sales") Double sales) {
		Userinfo usrinfo = userinfoService.findUser(userId);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
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
		order.setAddress(usrinfo.getAddress());
		order.setNumberByOrder(1);
		order.setDateCreated(timestamp);
		order.setPayTime(timestamp);
		order.setUser(usrinfo);
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
