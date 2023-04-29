package com.kh.baribari.admin.controller;

import com.google.gson.Gson;
import com.kh.baribari.admin.domain.*;
import com.kh.baribari.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

}
