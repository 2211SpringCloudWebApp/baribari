package com.kh.baribari.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.common.JsonParse;
import com.kh.baribari.common.ReturnUser;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.service.SellerService;
import com.kh.baribari.user.service.UserService;

@Controller
public class SellerController {
    @Autowired
    private SellerService sService;
    @Autowired
    private JsonParse jsonParse;
    @Autowired
    private UserService uService;
    @Autowired
    private ProductService pService;
    @Autowired
    @Qualifier("fileUpload")
    private FileInfo fileUpload;
    @Autowired
    private ReturnUser returnUser;

    /* ------------ 판매자 부분 ------------ */
    // 판매자가 판매하는 상품 목록 (상품등록/수정/삭제는 product패키지에 존재)
    @GetMapping("myPageSeller/list")
    public ModelAndView getProductList(ModelAndView mv, Authentication authentication) {
    	User user = returnUser.returnUser(authentication);
    	List<Product> pList = pService.getProductListBySeller(user.getUserNo());
    	System.out.println(pList);
    	mv.addObject("pList", pList).setViewName("");
    	return mv;
    }
}
