package com.kh.baribari.admin.controller;

import com.kh.baribari.admin.domain.CommunityList;
import com.kh.baribari.admin.domain.ProductList;
import com.kh.baribari.admin.domain.QnaList;
import com.kh.baribari.admin.domain.ReportList;
import com.kh.baribari.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private AdminService aService;

    @GetMapping("/admin")
    public ModelAndView adminView(
            ModelAndView mv
    ){
        List<ReportList> reportList = aService.selectReportList();
        List<CommunityList> communityList = aService.selectCommunityList();
        List<QnaList> qnaList = aService.selectQnaList();
        List<ProductList> productLists = aService.selectProductList();

        mv.addObject("reportList",reportList);
        mv.addObject("communityList",communityList);
        mv.addObject("qnaList",qnaList);
        mv.addObject("productLists",productLists);
        mv.setViewName("/myPageAdmin/adminPage");
        return mv;
    }
}
