package com.kh.baribari.product.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.FileUpload;
import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.review.service.ReviewService;
import com.kh.baribari.user.service.UserService;

@Controller
@RequestMapping("shopping")
public class ProductController {
	@Autowired
	private ProductService pService;
	@Autowired
	private ReviewService rService;
	@Autowired
	private UserService uService;
	@Autowired
	@Qualifier("fileUpload")
	private FileUpload fileUpload;

	// 상품 목록 및 갯수 출력
	@GetMapping("/list")
	public ModelAndView getProductList(ModelAndView mv,
			@RequestParam(value = "category", required = false, defaultValue = "All") String productCategory,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		// 상품 총 갯수
		int pCount = pService.getProductCount(productCategory);
		// PageInfo 매개변수: 현재페이지 (RequestParam), 전체 게시글 수 (mapper), 페이지 당 게시글 수
		PageInfo pi = new PageInfo(currentPage, pCount, 20);
		// 상품 목록
		List<Product> pList = pService.getProductList(productCategory, pi);

		if (pList != null) {
			mv.addObject("pList", pList);
			mv.addObject("pCount", pCount);
			mv.addObject("pi", pi);
			mv.setViewName("shopping/list");
			return mv;
		} else {
			mv.addObject("msg", "오류").setViewName("error");
			return mv;
		}
	}

	// 헤더에서 검색한 상품 목록
	@GetMapping("/search")
	public ModelAndView getProductSearchList(ModelAndView mv, Search search,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage) {
		// 상품 총 갯수
		int pCount = pService.getProductCountByKeyword(search);
		// PageInfo 매개변수: 현재페이지 (RequestParam), 전체 게시글 수 (mapper), 페이지 당 게시글 수
		PageInfo pi = new PageInfo(currentPage, pCount, 20);
		// 상품 목록
		List<Product> pList = pService.getProductList(pi, search);

		if (pList != null) {
			mv.addObject("pList", pList);
			mv.addObject("pCount", pCount);
			mv.addObject("pi", pi);
			mv.setViewName("shopping/search");
			return mv;
		} else {
			mv.addObject("msg", "오류").setViewName("error");
			return mv;
		}
	}

	// 상품 상세 페이지 (상품 정보, 추천 상품 목록)
	@GetMapping("/detail")
	public ModelAndView getProductDetail(ModelAndView mv, int productNo) {
		// 상품 정보
		Product product = pService.getProductDetail(productNo);
		// 상품 분류에 따른 추천 상품 목록
		List<Product> pList = pService.getProductRecommendList(product.getProductCategory());
		// 상품에 대한 후기 갯수
		int reviewCount = rService.getReviewCount(productNo);
		// 상품을 구매한 회원인지 확인 여부
		int customer = uService.checkCustomer(productNo);

		mv.addObject("product", product);
		mv.addObject("pList", pList);
		mv.addObject("customer", customer);
		mv.addObject("reviewCount", reviewCount);
		mv.setViewName("shopping/detail");
		return mv;
	}

	// 상품 등록 페이지 뷰
	@GetMapping("/register")
	public String registerProduct(Model model) {
		return "shopping/register";
	}
	
	// 상품 등록
	@PostMapping("/registerProduct")
	public ModelAndView registerProduct(
			@RequestParam(value = "fileList", required = false) List<MultipartFile> fList
			, @ModelAttribute Product product
			, HttpServletRequest request
			, ModelAndView mv
			) throws Exception {
		System.out.println("fList : " + fList);
		System.out.println("product : " + product);
//		Map<String, String> fMap = new HashMap<String, String>();
//		String path = "product";
//		int i = 1;
//		if (fList != null) {
//        	for (MultipartFile file : fList) {
//        		Map<String, String> fileInfo = fileUpload.saveFile(file, request, path);
//        		for (String k : fileInfo.keySet()) {
//        			String key = "file" + i;
//        			String value = fileInfo.get(k);
//        			fMap.put(key, value);
//        			if (i == 1) {
//        				product.setProductPic1(value);
//        			} else if (i == 2) {
//        				product.setProductPic2(value);
//        			} else if (i == 3) {
//        				product.setProductPic3(value);
//        			} else if (i == 4) {
//        				product.setProductPic4(value);
//        			}
//        		}
//        		i++;
//        	}
//		}
//		int result = pService.registerProduct(product);
//		if (result > 0) {
//			mv.setViewName("shopping/list");
//		} else {
//			mv.addObject("msg", "오류").setViewName("error");
//		}
//		return mv;
		mv.setViewName("/");
		return mv;
	}
}
