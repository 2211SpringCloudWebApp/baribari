package com.kh.baribari.admin.controller;

import com.google.gson.Gson;
import com.kh.baribari.admin.domain.*;
import com.kh.baribari.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService aService;
    @Autowired
    private Gson gson;

    @GetMapping("admin")
    public ModelAndView adminView(
            ModelAndView mv
    ) {
        List<ReportList> reportList = aService.selectReportList();
        List<CommunityList> communityList = aService.selectCommunityList();
        List<QnaList> qnaList = aService.selectQnaList();
        List<ProductList> productLists = aService.selectProductList();

        mv.addObject("reportList", reportList);
        mv.addObject("communityList", communityList);
        mv.addObject("qnaList", qnaList);
        mv.addObject("productLists", productLists);
        mv.setViewName("/myPageAdmin/adminPage");
        return mv;
    }

    @GetMapping("admin/user")
    public ModelAndView adminUserView(
            ModelAndView mv
    ) {
        List<UserList> uList = aService.selectUserList();
        mv.addObject("userList", uList);
        mv.setViewName("/myPageAdmin/menu/adminUser");
        return mv;
    }

    @GetMapping("admin/qna")
    public ModelAndView adminQna(
            ModelAndView mv
    ) {
        List<QnaList> qnaList = aService.selectQnaListByUser();
        mv.addObject("qnaList", qnaList);
        mv.setViewName("/myPageAdmin/menu/adminQna");
        return mv;
    }

    @GetMapping("admin/qnaDetail")
    public ModelAndView adminQnaDetail(
            @RequestParam int qnaNo,
            ModelAndView mv
    ) {
        QnaList qna = aService.selectQnaDetailByQnaNo(qnaNo);
        mv.addObject("qna", qna);
        mv.setViewName("myPageAdmin/menu/adminQnaDetail");
        return mv;
    }

    @GetMapping("admin/qnaModify")
    public ModelAndView qnaModifyView(
            ModelAndView mv,
            @RequestParam int qnaNo
    ) {
        QnaList qnaList = aService.selectQnaDetailByQnaNo(qnaNo);
        mv.addObject("qnaList", qnaList);
        mv.setViewName("myPageAdmin/menu/adminQnaModify");
        return mv;
    }

    @GetMapping("admin/product")
    public ModelAndView productAdminView(
            ModelAndView mv
    ) {
        List<ProductList> productList = aService.selectProductListByAdmin();
        mv.addObject("productList", productList);
        mv.setViewName("myPageAdmin/menu/adminProduct");
        return mv;
    }

    @GetMapping("admin/report")
    public ModelAndView reportAdminView(
            ModelAndView mv
    ) {
        List<ReportList> reportList = aService.selectReportList();
        mv.addObject("reportList", reportList);
        mv.setViewName("myPageAdmin/menu/adminReport");
        return mv;
    }


    @PostMapping("qnaModifySave")
    @ResponseBody
    public String qnaModifySave(
            @ModelAttribute QnaList qnalist
    ) {
        int result = aService.updateAnswerByAdmin(qnalist);
        if (result > 0) {
            return "<script>alert('저장되었습니다.'); location.href='/admin/qnaDetail?qnaNo=" + qnalist.getQnaNo() + "';</script>";
        } else {
            return "<script>alert('저장에 실패했습니다. 다시시도 해주세요.'); history.back();</script>";
        }
    }

    @PostMapping("/reportModalLoading")
    @ResponseBody
    public String ajaxReportModalLoading(
            int reportNo
    ) {
        ReportList reportList = aService.selectReportListByReportNo(reportNo);
        return gson.toJson(reportList);
    }

    @PostMapping("userBlock")
    @ResponseBody
    public String ajaxUserBlock(
            int userNo
    ) {
        int result = aService.updateUserBlock(userNo);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    @PostMapping("userUnBlock")
    @ResponseBody
    public String ajaxUserUnBlock(
            int userNo
    ) {
        int result = aService.updateUserUnBlock(userNo);
        if (result > 0) {
            return "성공";
        } else {
            return "실패";
        }
    }

    @PostMapping("ajaxMdOn")
    @ResponseBody
    public String ajaxMdOn(
            int productNo,
            int mdYn
    ) {
        int result = aService.updateMdYn(productNo, mdYn);

        return "";
    }

    @PostMapping("reportInnocence")
    @ResponseBody
    public String reportInnocence(
            @ModelAttribute ReportList reportList
    ) {
        int result = aService.updateReportInno(reportList);
        if (result > 0) {
            return "성공";
        }else {
            return "실패";
        }
    }

    @PostMapping("reportComplete")
    @ResponseBody
    public String reportComplete(
            @ModelAttribute ReportList reportList
    ){
        int result = aService.updateReportComplete(reportList);
        if (result > 0) {
            return "성공";
        }else {
            return "실패";
        }
    }

}
