package com.kh.baribari.admin.repository;

import com.kh.baribari.admin.domain.*;

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

    //  ajax 신고 상세 조회 / param - reportNo
    ReportList selectReportListByReportNo(int reportNo);

    //  유저관리 > 유저리스트 로딩
    List<UserList> selectUserList();

    //  유저 차단 / ajax, userNo
    int updateUserBlock(int userNo);

    //  유저 활성화 / ajax, userNo
    int updateUserUnBlock(int userNo);

    //  문의관리 > 문의리스트 로딩
    List<QnaList> selectQnaListByUser();
//  문의관리 > 문의상세페이지
    QnaList selectQnaDetailByQnaNo(int qnaNo);
//  문의관리 > 문의답변작성

    int updateAnswerByAdmin(QnaList qnalist);
}
