package com.kh.baribari.admin.service;

import com.kh.baribari.admin.domain.*;

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
//  ajax 신고 상세 조회 / param - reportNo
    ReportList selectReportListByReportNo(int reportNo);
//  회원관리 > 유저 목록 로딩
    List<UserList> selectUserList();
//  유저 비활성화 / ajax, userNo
    int updateUserBlock(int userNo);
//  유저 비활성화 / ajax, userNo
    int updateUserUnBlock(int userNo);
}
