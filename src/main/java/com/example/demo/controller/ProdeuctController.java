package com.example.demo.controller;

import java.io.File;
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

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;
import com.example.demo.util.PhotoAdd;

/**商品
 * @author geng9516
 *
 */
@Controller
public class ProdeuctController {

	@Autowired
	private ProductService productService;

	/**商品一覧表示
	 * @param model
	 * @return
	 */
	@RequestMapping("/productAll")
	public String productAll(Model model) {
		List<Product> productList = productService.findProducts();
		model.addAttribute("productList", productList);
		return "productAll";
	}

	/**商品追加画面へ
	 * @param model
	 * @return
	 */
	@RequestMapping("/productAdd")
	public String productAdd(Model model) {
		return "productAdd";
	}

	//写真を読み取るときのpath
	@Value("${PRODUCT_IMG}")
	private String productUrl;
	//写真を保存用の相対path
	@Value("${PRODUCT_PATH}")
	private String productPath;

	/**追加操作
	 * @param photo
	 * @param productType
	 * @param productIntro
	 * @param status
	 * @param sales
	 * @param cost
	 * @param stock
	 * @return
	 */
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public RedirectView saveProduct(
			@RequestParam(name = "photo") MultipartFile photo,
			@RequestParam(name = "productType") String productType,
			@RequestParam(name = "productIntro") String productIntro,
			@RequestParam(name = "status") String status,
			@RequestParam(name = "sales") Double sales,
			@RequestParam(name = "cost") Double cost,
			@RequestParam(name = "stock") Integer stock) {
		File newName = PhotoAdd.AddPhoto(photo, productPath);
		RedirectView redirectTarget = new RedirectView();
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Product product = new Product();
		product.setPhoto(productUrl + newName.toString());
		product.setProductType(productType);
		product.setProductIntro(productIntro);
		product.setStatus(status);
		product.setSales(sales);
		product.setCost(cost);
		product.setStock(stock);
		product.setDateCreated(timestamp);
		productService.save(product);
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	/**商品編集画面へ
	 * @param model
	 * @param productId　規定値を表するための検索条件
	 * @return
	 */
	@RequestMapping("/productEdit")
	public String productEdit(
			Model model,
			@RequestParam(name = "productId") Integer productId) {
		Product product = productService.findProductById(productId);
		model.addAttribute("product", product);
		return "productEdit";
	}

	/**編集した値を受け取り、更新する操作
	 * @param photo
	 * @param productId
	 * @param productType
	 * @param productIntro
	 * @param status
	 * @param sales
	 * @param cost
	 * @param stock
	 * @return
	 */
	@RequestMapping("/editProduct")
	public RedirectView editProduct(
			@RequestParam(name = "photo") MultipartFile photo,
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "productType") String productType,
			@RequestParam(name = "productIntro") String productIntro,
			@RequestParam(name = "status") String status,
			@RequestParam(name = "sales") Double sales,
			@RequestParam(name = "cost") Double cost,
			@RequestParam(name = "stock") Integer stock) {
		RedirectView redirectTarget = new RedirectView();
		Product product = productService.findProductById(productId);

		if (!photo.isEmpty()) {
			File newName = PhotoAdd.AddPhoto(photo, productPath);
			product.setPhoto(productUrl + newName);
		}
		if (productType != null) {
			product.setProductType(productType);
		}
		if (!productIntro.isEmpty()) {
			product.setProductIntro(productIntro);
		}
		if (!status.isEmpty()) {
			product.setStatus(status);
		}
		if (sales != null) {
			product.setSales(sales);
		}
		if (cost != null) {
			product.setCost(cost);
		}
		if (stock != null) {
			product.setStock(stock);
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		product.setDateModified(timestamp);
		productService.save(product);
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	/**商品の状態を変更
	 * @param productId
	 * @return
	 */
	@RequestMapping("/deleteProduct")
	public RedirectView deleteProduct(
			@RequestParam(name = "productId") Integer productId) {
		RedirectView redirectTarget = new RedirectView();
		//productIdで状態を更新する
		productService.deleteProductById(productId, "出品停止");
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

	/**曖昧検索
	 * @param productId
	 * @param productType
	 * @param productIntro
	 * @param sales1
	 * @param sales2
	 * @param cost1
	 * @param cost2
	 * @param stock1
	 * @param stock2
	 * @return
	 */
	@RequestMapping("/searchProduct")
	public String searchProduct(Model model,
			@RequestParam(name = "productId") Integer productId,
			@RequestParam(name = "productType") String productType,
			@RequestParam(name = "productIntro") String productIntro,
			@RequestParam(name = "sales1") Double sales1,
			@RequestParam(name = "sales2") Double sales2,
			@RequestParam(name = "cost1") Double cost1,
			@RequestParam(name = "cost2") Double cost2,
			@RequestParam(name = "stock1") Integer stock1,
			@RequestParam(name = "stock2") Integer stock2) {
		List<Product> productList = new ArrayList<>();
		if (productId != null) {
			//productIdで曖昧検索
			productList = productService.findAllById(productId);
			if (!productIntro.isEmpty()) {
				//productIdと商品情報で曖昧検索
				productList = productService.findAllByIdAndProductIntro(productId, productIntro);
			}
		} else if (!productIntro.isEmpty()) {
			//商品情報で曖昧検索
			productList = productService.findAllByProductIntro(productIntro);
		} else if (!productType.isEmpty()) {
			//商品タイプで曖昧検索
			productList = productService.findAllByProductType(productType);
		} else if (sales1 != null && sales2 != null) {
			//sales1とsales2間の価格検索
			productList = productService.findAllByBetweenSales1AndSales2(sales1, sales2);
		} else if (cost1 != null && cost2 != null) {
			//コスト値で曖昧検索
			productList = productService.findAllByCost(cost1,cost2);
		} else if(stock1 != null && stock2 != null){
			//在庫で曖昧検索
			productList = productService.findAllByStock(stock1,stock2);
		}else {
			//全検査
			productList = productService.findProducts();
		}
		model.addAttribute("productList", productList);
		return "productAll";
	}

	/**一覧へ戻る
	 * @return
	 */
	@RequestMapping("/backProductAll")
	public RedirectView backProductAll() {
		RedirectView redirectTarget = new RedirectView();
		redirectTarget.setUrl("productAll");
		return redirectTarget;
	}

}
