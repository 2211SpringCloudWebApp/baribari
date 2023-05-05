package com.kh.baribari.user.controller;

import com.kh.baribari.common.FileInfo;
import com.kh.baribari.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kh.baribari.common.JsonParse;
import com.kh.baribari.security.auth.PrincipalDetails;
import com.kh.baribari.user.service.UserService;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService uService;

    @Autowired
    private JsonParse jsonParse;

    @Autowired
    @Qualifier("fileUpload")
    private FileInfo fileUpload;

    //    로그인 뷰
    @GetMapping("login")
    public String loginView() {
        return "login/login";
    }

    //    회원가입 뷰
    @GetMapping("register")
    public String registerView() {
        return "login/register";
    }

    @GetMapping("registerUser")
    public String registerUserView() {
        return "login/registerUser";
    }

    @GetMapping("registerSeller")
    public String registerSellerView() {
        return "login/registerSeller";
    }

    //    회원가입 id 유효성 체크
    @GetMapping("ajaxCheckId")
    @ResponseBody
    public String ajaxIdCheck(String id) {
        User user = uService.selectIdCheck(id);
        String result;
        if (user != null) {
            result = "중복";
        } else {
            result = "사용가능";
        }
        return jsonParse.returnJson(result);
    }

    //  회원가입 닉네임 유효성 체크 ajax
    @GetMapping("ajaxNickNameCheck")
    @ResponseBody
    public String ajaxNickNameCheck(String nickName) {
        User user = uService.selectNickNameCheck(nickName);
        String result;
        if (user != null) {
            result = "중복";
        } else {
            result = "사용가능";
        }
        return jsonParse.returnJson(result);
    }

    //    유저 회원가입 로직
    @PostMapping("registerUserProc")
    @ResponseBody
    public String registerUserProc(@ModelAttribute User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        int result = uService.insertUserByUser(user);
        if (result > 0) {
            return "<script>alert('" + user.getUserNickName() + "님 환영합니다!'); location.href='/';</script>";
        } else {
            return "<script>alert('회원가입에 실패하였습니다. \n 관리자에게 문의하시거나 다시 시도해주세요.'); location.href='/register';</script>";
        }
    }

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

    // 아이디 및 비밀번호 찾기 뷰
    @GetMapping("/find")
    public String findIdPwView() {
        return "login/find";
    }

    // 아이디 찾기
    @PostMapping("/findId")
    public String findId(
            @ModelAttribute User user,
            Model model
    ) {
        String userId = uService.findUserId(user);
        if (userId != null) {
            model.addAttribute("userId", userId);
            return "login/resultId";
        } else {
            model.addAttribute("error", "일치하는 아이디가 없습니다.");
            return "login/find";
        }
    }

    // 비밀번호 찾기
    @PostMapping("/findPw")
    public String findPw(
            @ModelAttribute User user,
            Model model
    ) {
        int result = uService.findUserPw(user);
        if (result > 0) {
            model.addAttribute("user", user);
            return "login/pwChange";
        } else {
            model.addAttribute("error", "일치하는 정보가 없습니다.");
            return "login/find";
        }
    }

    @PostMapping("/changePw")
    public String pwChange(
            @ModelAttribute User user,
            Model model
    ) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setUserPw(bCryptPasswordEncoder.encode(user.getUserPw()));
        int result = uService.pwChange(user);
        if (result > 0) {
            model.addAttribute("error", "변경완료! 로그인 해주세요.");
            return "login/login";
        }else {
            model.addAttribute("user",user);
            model.addAttribute("error", "변경실패 다시 시도해주세요.");
            return "login/pwChange";
        }
    }

    //    유저-마이페이지 뷰
    @GetMapping("myPageUser")
    public String myPageUserView(
            Authentication authentication,
            Model model
    ) {
        User user = returnUser(authentication);
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
        User userData = returnUser(authentication);
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
        User user = returnUser(authentication);
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

    // 배송지 관리 뷰
    @GetMapping("myPageUser/address")
    public String myPageUserAddressView(Authentication authentication, Model model) {
        User user = returnUser(authentication);
        List<Address> alist = uService.selectAddressList(user);
        model.addAttribute("aList", alist);
        return "myPage/information/Address";
    }

    //    배송지 추가 컨트롤러
    @PostMapping("addAddress")
    @ResponseBody
    public String addAddressByUser(Authentication authentication, @ModelAttribute Address address) {
        address.setUserNo(returnUser(authentication).getUserNo());
        int result = uService.insertAddressByUser(address);
        return "<script>alert('배송지가 추가되었습니다!'); location.href='/myPageUser/address';</script>";
    }

    //   배송지 삭제
    @PostMapping("ajaxRemoveAddress")
    @ResponseBody
    public String ajaxRemoveAddress(int addressNo) {
        int result = uService.deleteAddress(addressNo);
        if (result > 0) {
            return jsonParse.returnJson("성공");
        } else {
            return jsonParse.returnJson("실패");
        }
    }

    // 세션에서 user 를 꺼내줌
    private User returnUser(Authentication authentication) {
        PrincipalDetails userDetails = (PrincipalDetails) authentication.getPrincipal();
        return userDetails.getUser();
    }

    //    유저 1:1 문의 뷰
    @GetMapping("/myPageUser/qna")
    public String myPageUserQnaView(Authentication authentication, Model model, @RequestParam String qnaAnswerYn) {
        User user = returnUser(authentication);
        MyPageQna qna = new MyPageQna(user.getUserNo(), qnaAnswerYn);
        List<MyPageQna> qnaList = uService.selectQna(qna);
        model.addAttribute("qnaList", qnaList);
        return "myPage/qna/qna";
    }

    //    문의 상세페이지
    @GetMapping("/myPageUser/qnaDetail")
    public String myPageUserQnaDetailView(@RequestParam int qnaNo, Model model) {
        MyPageQna qna = uService.selectQnaDetail(qnaNo);
        model.addAttribute("qnaDetail", qna);
        return "myPage/qna/qnaDetail";
    }

    //    문의 삭제
    @PostMapping("/ajaxRemoveQna")
    @ResponseBody
    public String ajaxRemoveQna(int qnaNo) {
        int result = uService.qnaRemove(qnaNo);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    //    문의 수정 뷰
    @GetMapping("/myPageUser/qnaModify")
    public String myPageUserQnaModify(@RequestParam int qnaNo, Model model) {
        MyPageQna qna = uService.selectQnaDetail(qnaNo);
        model.addAttribute("qnaDetail", qna);
        return "myPage/qna/qnaModify";
    }

    //    문의 수정 저장
    @PostMapping("/qnaModify/save")
    @ResponseBody
    public String myPageUserQnaModifySave(@ModelAttribute MyPageQna myPageQna, MultipartFile file, HttpServletRequest request) {
        int result = uService.qnaModifySave(myPageQna);
        if (result > 0) {
            return "<script>alert('수정이 정상적으로 끝났습니다!'); location.href='/myPageUser/qna?qnaAnswerYn=all';</script>";
        } else {
            return "<script>alert('수정이 정상적으로 끝났습니다!'); location.href='/myPageUser/qna?qnaAnswerYn=all';</script>";
        }
    }

    //    문의 작성 뷰
    @GetMapping("/myPageUser/qnaWrite")
    public String myPageQnaWriteView() {
        return "myPage/qna/qnaWrite";
    }

    //    문의 작성 로직
    @PostMapping("/qnaWrite/save")
    @ResponseBody
    public String myPageQnaWriteSave(
            @RequestParam(name = "qnaPic1") MultipartFile qnaPic1,
            @RequestParam(name = "qnaPic2") MultipartFile qnaPic2,
            @RequestParam int userNo,
            HttpServletRequest request,
            @RequestParam String qnaContent
    ) throws Exception {
        String path = "myPageUser\\qna";
        String filePath1 = null, filePath2 = null;
        if (!qnaPic1.isEmpty()) {
            Map<String, String> file = fileUpload.saveFile(qnaPic1, request, path);
            filePath1 = file.get("filePath");
        }
        if (!qnaPic2.isEmpty()) {
            Map<String, String> file2 = fileUpload.saveFile(qnaPic2, request, path);
            filePath2 = file2.get("filePath");
        }

        MyPageQna myPageQna = new MyPageQna(qnaContent, userNo, filePath1, filePath2);
        int result = uService.qnaWrite(myPageQna);
        if (result > 0) {
            return "<script>alert('저장이 잘됐어요!'); location.href='/myPageUser/qna?qnaAnswerYn=all';</script>";
        } else {
            return "<script>alert('저장에 실패했어요ㅠㅠ'); location.href='/myPageUser/qna?qnaAnswerYn=all';</script>";
        }
    }


    //    상품 문의 뷰
    @GetMapping("/myPageUser/productQna")
    public String productQnaView(Authentication authentication, Model model) {
        User user = returnUser(authentication);
        List<MyPageQna> myPageQnaList = uService.selectProductQna(user);
        model.addAttribute("qnaList", myPageQnaList);
        return "myPage/qna/productQna";
    }


    //    주문배송조회 뷰
    @GetMapping("myPageUser/orderLogistic")
    public String orderLogisticView(Model model, Authentication authentication, @RequestParam String startDate, @RequestParam String endDate) {
        User user = returnUser(authentication);
        MyPageOrderList myPageOrderListParam = new MyPageOrderList(user.getUserNo(), startDate, endDate);
        List<MyPageOrderList> myPageOrderList = uService.selectOrderList(myPageOrderListParam);
        model.addAttribute("orderList", myPageOrderList);
        return "myPage/order/order-logistic";
    }


    //  장바구니 뷰
    @GetMapping("myPageUser/cart")
    public String cartView(Authentication authentication, Model model) {
        User user = returnUser(authentication);
        List<CartList> cartList = uService.selectCartList(user.getUserNo());
        model.addAttribute("cartList", cartList);
        return "myPage/order/cart";
    }

    //    장바구니 카운트 up / ajax
    @GetMapping("ajaxEaUpDown")
    @ResponseBody
    public String cartCountUp(int userNo, int productNo, int productQuantity) {
        CartList cartList = new CartList(productNo, productQuantity, userNo);
        int result = uService.cartCountUpDown(cartList);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    //    장바구니 삭제
    @PostMapping("cartRemove")
    @ResponseBody
    public String cartRemove(int userNo, int productNo) {
        CartList cartList = new CartList(productNo, userNo);
        int result = uService.cartRemove(cartList);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    //    찜한 상품 뷰
    @GetMapping("myPageUser/like")
    public String likeView(Authentication authentication, Model model) {
        User user = returnUser(authentication);
        List<Favorite> favoriteList = uService.selectFavorite(user.getUserNo());
        model.addAttribute("favoriteList", favoriteList);
        return "myPage/order/like";
    }

    //    찜한 상품 삭제
    @PostMapping("favoriteDel")
    @ResponseBody
    public String favoriteDelete(int userNo, int productNo) {
        Favorite favorite = new Favorite(productNo, userNo);
        int result = uService.deleteFavorite(favorite);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    // 커뮤니티
    // 팩다운뷰
    @GetMapping("pegDown")
    public ModelAndView pegDownView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser(authentication);
        List<MPCommunityList> CommunityList = uService.selectPegDownList(user);
        mv.addObject("communityList", CommunityList);
        mv.setViewName("/myPage/community/pegDown");
        return mv;
    }

    // 내가쓴글 뷰
    @GetMapping("myWrite")
    public ModelAndView myWriteView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser(authentication);
        List<MPCommunityList> CommunityList = uService.selectMyWrite(user);
        mv.addObject("communityList", CommunityList);
        mv.setViewName("/myPage/community/myWrite");
        return mv;
    }

    // 내가쓴글 뷰
    @GetMapping("myComment")
    public ModelAndView myCommentView(
            ModelAndView mv,
            Authentication authentication
    ){
        User user = returnUser(authentication);
        List<CommentList> CommentList = uService.selectCommentList(user);
        mv.addObject("CommentList", CommentList);
        mv.setViewName("/myPage/community/myComment");
        return mv;
    }
}
