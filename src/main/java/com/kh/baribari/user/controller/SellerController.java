package com.kh.baribari.user.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.kh.baribari.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import com.kh.baribari.product.domain.Order;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.service.OrderService;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.security.auth.PrincipalDetails;
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
	private OrderService oService;
	@Autowired
	private ReturnUser returnUser;
	@Autowired
	@Qualifier("fileUpload")
	private FileInfo fileUpload;

    //  판매자 회원가입 로직
    @PostMapping("registerSellerProc")
    @ResponseBody
    public String registerSellerProc(@ModelAttribute User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int result = uService.insertUserBySeller(user);
        if (result > 0) {
            return "<script>alert('" + user.getUserNickName() + "님 환영합니다!'); location.href='/';</script>";
        } else {
            return "<script>alert('회원가입에 실패하였습니다. \n 관리자에게 문의하시거나 다시 시도해주세요.'); location.href='/register';</script>";
        }
    }

    @GetMapping("/myPageSeller")
    public String myPageSellerView(
            Authentication authentication,
            Model model
    ){
        User user = returnUser.returnUser(authentication);
        UserMyPageData userUserMyPageData = new UserMyPageData(user.getUserNo(), user.getUserLevelPoint());
        UserMyPageData userMyPageData = sService.selectMyPageSeller(userUserMyPageData);
        model.addAttribute("userMyPageData", userMyPageData);
        return "myPageSeller/myPageSeller";
    }

	@GetMapping("myPageSeller/modify")
	public String myPageUserModifyView(Authentication authentication, Model model) {
		User userData = returnUser.returnUser(authentication);
		User user = uService.selectModifyUser(userData.getUserNo());
		model.addAttribute("user", user);
		return "myPageSeller/information/sellerModify";
	}


    //  회원탈퇴 뷰
    @GetMapping("myPageSeller/withdraw")
    public String withdrawView() {
        return "myPageSeller/information/withdraw";
    }

    //    유저 1:1 문의 뷰
    @GetMapping("/myPageSeller/qna")
    public String myPageUserQnaView(Authentication authentication, Model model, @RequestParam String qnaAnswerYn) {
        User user = returnUser.returnUser(authentication);
        MyPageQna qna = new MyPageQna(user.getUserNo(), qnaAnswerYn);
        List<MyPageQna> qnaList = uService.selectQna(qna);
        model.addAttribute("qnaList", qnaList);
        return "myPageSeller/qna/qna";
    }

    //    문의 작성 뷰
    @GetMapping("/myPageSeller/qnaWrite")
    public String myPageQnaWriteView() {
        return "myPage/qna/qnaWrite";
    }

    //    문의 상세페이지
    @GetMapping("/myPageSeller/qnaDetail")
    public String myPageUserQnaDetailView(@RequestParam int qnaNo, Model model) {
        MyPageQna qna = uService.selectQnaDetail(qnaNo);
        model.addAttribute("qnaDetail", qna);
        return "myPageSeller/qna/qnaDetail";
    }

    //    상품 문의 뷰
    @GetMapping("/myPageSeller/productQna")
    public String productQnaView(Authentication authentication, Model model) {
        User user = returnUser.returnUser(authentication);
        List<MyPageQna> myPageQnaList = uService.selectProductQna(user);
        model.addAttribute("qnaList", myPageQnaList);
        return "myPageSeller/qna/productQna";
    }

    //    문의 수정 뷰
    @GetMapping("/myPageSeller/qnaModify")
    public String myPageUserQnaModify(@RequestParam int qnaNo, Model model) {
        MyPageQna qna = uService.selectQnaDetail(qnaNo);
        model.addAttribute("qnaDetail", qna);
        return "myPageSeller/qna/qnaModify";
    }

    //    신고내역 뷰
    @GetMapping("myPageSeller/report")
    public ModelAndView reportView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser.returnUser(authentication);
        List<MyPageReportList> myPageReportList = uService.selectReportList(user);
        mv.addObject("reportList", myPageReportList);
        mv.setViewName("/myPageSeller/qna/report");
        return mv;
    }


    // 커뮤니티
    // 팩다운뷰
    @GetMapping("myPageSeller/pegDown")
    public ModelAndView pegDownView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser.returnUser(authentication);
        List<MPCommunityList> CommunityList = uService.selectPegDownList(user);
        mv.addObject("communityList", CommunityList);
        mv.setViewName("/myPageSeller/community/pegDown");
        return mv;
    }

    // 내가쓴글 뷰
    @GetMapping("myPageSeller/myWrite")
    public ModelAndView myWriteView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser.returnUser(authentication);
        List<MPCommunityList> CommunityList = uService.selectMyWrite(user);
        mv.addObject("communityList", CommunityList);
        mv.setViewName("/myPageSeller/community/myWrite");
        return mv;
    }
    // 내가쓴댓글 뷰
    @GetMapping("myPageSeller/myComment")
    public ModelAndView myCommentView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser.returnUser(authentication);
        List<CommentList> CommentList = uService.selectCommentList(user);
        mv.addObject("CommentList", CommentList);
        mv.setViewName("/myPageSeller/community/myComment");
        return mv;
    }

	/* ------------ 판매자 부분 ------------ */
	// 판매자가 판매하는 상품 목록 (상품등록/수정/삭제는 product패키지에 존재)
	@GetMapping("myPageSeller/list")
	public ModelAndView getProductList(ModelAndView mv, Authentication authentication) {
		User user = returnUser.returnUser(authentication);
		List<Product> pList = pService.getProductListBySeller(user.getUserNo());
		mv.addObject("pList", pList).setViewName("myPageSeller/product/list");
		return mv;
	}

	// 판매자가 판매하는 상품에 대한 주문 목록
	@GetMapping("myPageSeller/orderList")
	public ModelAndView getOrderList(ModelAndView mv, Authentication authentication) {
		User user = returnUser.returnUser(authentication);
		List<Order> oList = oService.getOrderList(user.getUserNo());
		mv.addObject("oList", oList).setViewName("myPageSeller/product/order");
		return mv;
	}
}
