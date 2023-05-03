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

    @GetMapping("myPageSeller")
    public String myPageSeller(Authentication authentication){
        User user = returnUser.returnUser(authentication);
        return "myPageSeller/myPageSeller";
    }

    //    유저-마이페이지 뷰
    @GetMapping("myPageUser")
    public String myPageUserView(
            Authentication authentication,
            Model model
    ) {
        User user = returnUser.returnUser(authentication);
        UserMyPageData userUserMyPageData = new UserMyPageData(user.getUserNo(), user.getUserLevelPoint());
        UserMyPageData userMyPageData = uService.selectUserMyPageData(userUserMyPageData);
        model.addAttribute("userMyPageData", userMyPageData);
        return "myPage/myPageUser";
    }

    //    유저-마이페이지 수정 뷰
    @GetMapping("myPageUser/modify")
    public String myPageUserModifyView(
            Authentication authentication,
            Model model
    ) {
        User userData = returnUser.returnUser(authentication);
        User user = uService.selectModifyUser(userData.getUserNo());
        model.addAttribute("user", user);
        return "myPage/information/UserModify";
    }

    //  유저-마이페이지 사진 수정 버튼
    @PostMapping("/profilePicSave")
    @ResponseBody
    public String myPageProfilePic(
            @RequestParam(name = "file1") MultipartFile qnaPic1,
            HttpServletRequest request,
            Authentication authentication
    ) throws Exception {
        User user = returnUser.returnUser(authentication);
        String path = "myPageUser\\profilePic";
        String filePath1 = null;
        if (!qnaPic1.isEmpty()) {
            Map<String, String> file = fileUpload.saveFile(qnaPic1, request, path);
            filePath1 = file.get("filePath");
        }
        user.setProfilePicPath(filePath1);
        int result = uService.updateProfilePic(user);
        if (result > 0) {
            return "<script>alert('저장이 잘됐어요!'); location.href='/myPageUser/modify';</script>";
        } else {
            return "<script>alert('저장에 실패했어요ㅠㅠ'); location.href='/myPageUser/modify';</script>";
        }
    }

    //  회원탈퇴 뷰
    @GetMapping("myPageUser/withdraw")
    public String withdrawView() {
        return "myPage/information/withdraw";
    }

    //    회원탈퇴 로직
    @PostMapping("withdrawUser")
    @ResponseBody
    public String withdrawUser(int userNo) {
        int result = uService.deleteUserByUserNo(userNo);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    // 유저 수정 페이지
    @PostMapping("myPageUser/modifySubmit")
    @ResponseBody
    public String modifySubmit(@ModelAttribute User user) {
        User updateByUser = uService.updateMyPageByUser(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        PrincipalDetails userDetails = (PrincipalDetails) auth.getPrincipal();
        userDetails.setUser(updateByUser);
        return "<script>alert('수정이 정상적으로 끝났습니다!'); location.href='/myPageUser/modify';</script>";
    }
    
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
