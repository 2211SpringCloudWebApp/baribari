package com.kh.baribari.product.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.review.service.ReviewService;
import com.kh.baribari.user.domain.Favorite;
import com.kh.baribari.user.domain.User;
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
	private ReturnUser returnUser;
	@Autowired
	@Qualifier("fileUpload")
	private FileInfo fileInfo;

	/* ---------------------------------------------------- 모든 사용자 ---------------------------------------------------- */
	// 상품 목록 및 갯수 출력
	@GetMapping("/list")
	public ModelAndView getProductList(ModelAndView mv,
			@RequestParam(value = "category", required = false, defaultValue = "All") String productCategory,
			@RequestParam(value = "page", required = false, defaultValue = "1") Integer currentPage
			) {
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
	public ModelAndView getProductDetail(ModelAndView mv, int productNo, Authentication authentication) {
		// 사용자 정보
		User user = null;
		Favorite favorite = new Favorite();
		int fResult = 0;
		if(authentication != null) {
			user = returnUser.returnUser(authentication);
			favorite.setUserNo(user.getUserNo());
			favorite.setProductNo(productNo);
			fResult = pService.getFavorite(favorite);
		}
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
		mv.addObject("favorite", fResult);
		mv.setViewName("shopping/detail");
		return mv;
	}
	
	// 찜하기 추가
	@PostMapping("/favorite/add")
	@ResponseBody
	public String addFavorite(Favorite favorite) {
		int result = pService.addFavorite(favorite);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}
	
	// 찜하기 제거
	@PostMapping("/favorite/remove")
	@ResponseBody
	public String removeFavorite(Favorite favorite) {
		int result = pService.removeFavorite(favorite);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/* ---------------------------------------------------- 판매자 ---------------------------------------------------- */
	// 상품 등록 페이지 뷰
	@GetMapping("/register")
	public ModelAndView registerProduct(ModelAndView mv, Authentication authentication) {
		// 사용자 정보
		User user = returnUser.returnUser(authentication);
		if (user != null) {
			if (user.getUserType() == 2) {
				mv.setViewName("myPageSeller/product/register");
			} else if (user.getUserType() == 1) {
				mv.addObject("msg", "판매자만 이용이 가능합니다").setViewName("error");
			} else {
				mv.addObject("msg", "사용자 정보를 찾을 수 없습니다").setViewName("error");
			}
		} else {
			mv.addObject("msg", "사용자 정보를 찾을 수 없습니다").setViewName("error");
		}
		return mv;
	}
	
	// 상품 등록
	@PostMapping("/registerProduct")
	public ModelAndView registerProduct(
			@RequestParam(value = "mainImg", required = false) List<MultipartFile> fList 
			, @RequestParam(value = "descriptionImgs", required = false) List<MultipartFile> fList2
			, Product product
			, HttpServletRequest request
			, ModelAndView mv
			) throws Exception {
		Map<String, String> fMap = new HashMap<String, String>();
		String path = "product";
		fList.addAll(fList2);
		int i = 1;
		if (fList != null) {
        	for (MultipartFile file : fList) {
        		Map<String, String> files = fileInfo.saveFile(file, request, path);
        		for (String k : files.keySet()) {
        			String key = "file" + i;
        			String value = files.get(k);
        			fMap.put(key, value);
        			if (i == 1) {
        				product.setProductPic1(value);
        			} else if (i == 2) {
        				product.setProductPic2(value);
        			} else if (i == 3) {
        				product.setProductPic3(value);
        			} else if (i == 4) {
        				product.setProductPic4(value);
        			}
        		}
        		i++;
        	}
		}
		int result = pService.registerProduct(product);
		if (result > 0) {
			mv.setViewName("redirect:/shopping/list?category=All&page=1");
		} else {
			mv.addObject("msg", "오류").setViewName("error");
		}
		return mv;
	}
	
	// 상품 수정 페이지 뷰
	@PostMapping("/modify")
	public ModelAndView modifyProduct(ModelAndView mv, int productNo, Authentication authentication) {
		// 사용자 정보
		User user = returnUser.returnUser(authentication);
		if (user != null) {
			if (user.getUserType() == 2) {
				Product product = pService.getProductDetail(productNo);
				mv.addObject("product", product).setViewName("myPageSeller/product/modify");
			} else if (user.getUserType() == 1) {
				mv.addObject("msg", "판매자만 이용이 가능합니다").setViewName("error");
			} else {
				mv.addObject("msg", "사용자 정보를 찾을 수 없습니다").setViewName("error");
			}
		} else {
			mv.addObject("msg", "사용자 정보를 찾을 수 없습니다").setViewName("error");
		}
		return mv;
	}
	
	// 상품 수정
	@PostMapping("/modifyProduct")
	public ModelAndView modifyProduct(
			@RequestParam(value = "mainImg", required = false) List<MultipartFile> fList,
			@RequestParam(value = "descriptionImgs", required = false) List<MultipartFile> fList2,
			Product product,
			HttpServletRequest request, ModelAndView mv) throws Exception {
			
		Map<String, String> fMap = new HashMap<String, String>();
		String path = "product";
		fList.addAll(fList2);
		System.out.println(fList);

		// 기존 파일 삭제
		Product originalProduct = pService.getProductDetail(product.getProductNo());
		System.out.println(originalProduct.getProductPic1());
		if (originalProduct.getProductPic1() != null && !originalProduct.getProductPic1().isEmpty()) {
			fileInfo.deleteFile(request, originalProduct.getProductPic1());
		}
		if (originalProduct.getProductPic2() != null && !originalProduct.getProductPic2().isEmpty()) {
			fileInfo.deleteFile(request, originalProduct.getProductPic2());
		}
		if (originalProduct.getProductPic3() != null && !originalProduct.getProductPic3().isEmpty()) {
			fileInfo.deleteFile(request, originalProduct.getProductPic3());
		}
		if (originalProduct.getProductPic4() != null && !originalProduct.getProductPic4().isEmpty()) {
			fileInfo.deleteFile(request, originalProduct.getProductPic4());
		}

		int i = 1;
		if (fList != null) {
			for (MultipartFile file : fList) {
				String originalFileName = file.getOriginalFilename();
				String extension = "";
				int lastIndex = originalFileName.lastIndexOf('.');
				if (lastIndex != -1) {
					extension = originalFileName.substring(lastIndex + 1); // 확장자 추출
				}
				Map<String, String> files = fileInfo.saveFile(file, request, path);
				for (String k : files.keySet()) {
					String key = "file" + i;
					String value = files.get(k) + "." + extension; // 파일명 뒤에 확장자 추가
					fMap.put(key, value);
					if (i == 1) {
						product.setProductPic1(value);
					} else if (i == 2) {
						product.setProductPic2(value);
					} else if (i == 3) {
						product.setProductPic3(value);
					} else if (i == 4) {
						product.setProductPic4(value);
					}
				}
				i++;
			}
		}
		int result = pService.modifyProduct(product);
		if (result > 0) {
			mv.setViewName("redirect:/shopping/detail?productNo=" + product.getProductNo());
		} else {
			mv.addObject("msg", "오류").setViewName("error");
		}
		return mv;
	}



	
	// 상품 삭제
	@PostMapping("/delete")
	@ResponseBody
	public String deleteProduct(int productNo, HttpServletRequest request) throws Exception {
		// 정보 가져오기 → 사진이 있을 경우 사진 물리적 삭제 → 상품 삭제 (CASCADE로 인해 후기 사진 테이블의 자료도 자동 삭제)
		// 사진 정보를 가져오기 위해 product정보 가져오기
		Product product = pService.getProductDetail(productNo);
		String path = "product";
		// 사진이 있을 경우 삭제
		if (product.getProductPic1() != null && !product.getProductPic1().isEmpty()) {
		    fileInfo.deleteFile(request, product.getProductPic1());
		}
		if (product.getProductPic2() != null && !product.getProductPic2().isEmpty()) {
			fileInfo.deleteFile(request, product.getProductPic2());
		}
		if (product.getProductPic3() != null && !product.getProductPic3().isEmpty()) {
			fileInfo.deleteFile(request, product.getProductPic1());
		}
		int result = pService.deleteProduct(productNo);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}

	/* ---------------------------------------------------- 관리자 ---------------------------------------------------- */
	// MD 추천/제거
	@PostMapping("/mdRecommend")
	@ResponseBody
	public String mdRecommend(Product product) {
		int result = pService.mdRecommend(product);
		if (result > 0) {
			return "1";
		} else {
			return "0";
		}
	}
}
