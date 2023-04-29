package com.kh.baribari.admin.service;

import com.kh.baribari.admin.domain.CommunityList;
import com.kh.baribari.admin.domain.ProductList;
import com.kh.baribari.admin.domain.QnaList;
import com.kh.baribari.admin.domain.ReportList;

import java.util.List;

public interface AdminService {
//     신고 리스트 로딩
    List<ReportList> selectReportList();
//  커뮤니티 인기 글 리스트 로딩
    List<CommunityList> selectCommunityList();
//  qna 리스트 로딩
    List<QnaList> selectQnaList();
//  MD 상품 리스트 로딩
    List<ProductList> selectProductList();
}
