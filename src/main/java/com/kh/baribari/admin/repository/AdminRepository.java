package com.kh.baribari.admin.repository;

import com.kh.baribari.admin.domain.CommunityList;
import com.kh.baribari.admin.domain.ProductList;
import com.kh.baribari.admin.domain.QnaList;
import com.kh.baribari.admin.domain.ReportList;

import java.util.List;

public interface AdminRepository {
//     신고 리스트 로딩
    List<ReportList> selectReportList();
//  커뮤니티 인기 글 리스트 로딩
    List<CommunityList> selectCommunityList();
//  qna 리스트 로딩
    List<QnaList> selectQnaList();
//  md 상품 리스트 로딩
    List<ProductList> selectProductList();
}
