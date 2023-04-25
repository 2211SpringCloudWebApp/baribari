package com.kh.baribari.user.repository;

import java.util.List;

import com.kh.baribari.user.domain.*;

public interface UserRepository {

		User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);

    int insertUserByUser(User user);

    int insertUserBySeller(User user);

    User findByUserId(String userId);

    List<Role> findByUserNo(int userNo);

    UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData);

	int checkCustomer(Integer productNo);

	//공지게시판 상세조회
	User selectUserByuserId(String userId);

    User updateMyPageByUser(User user);

//     배송지 추가
    int insertAddressByUser(Address address);

//    배송지 리스트 가져오기
    List<Address> selectAddressList(User user);

	User getUserInfo(int userNo);

//    마이페이지 > 1:1 문의
    List<MyPageQna> selectQna(MyPageQna qna);
//  배송지 삭제
    int deleteAddress(int addressNo);

//    유저 회원 탈퇴
    int deleteUserByUser(int userNo);

//  문의 상세페이지
    MyPageQna selectQnaDetail(int answerNo);
//  문의 삭제
    int qnaRemove(int qnaNo);
//  문의 수정 저장
    int qnaModifySave(MyPageQna myPageQna);
//  문의사항 작성
    int qnaWrite(MyPageQna myPageQna);
//  상품 문의사항 뷰
    List<MyPageQna> selectProductQna(User user);
//  주문내역 조회
    List<MyPageOrderList> selectOrderList(int userNo);
}
