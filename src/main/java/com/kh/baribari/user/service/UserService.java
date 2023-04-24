package com.kh.baribari.user.service;

import java.util.List;

import com.kh.baribari.user.domain.Address;
import com.kh.baribari.user.domain.MyPageQna;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.domain.UserMyPageData;

public interface UserService {
    User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);

    int insertUserByUser(User user);

    int insertUserBySeller(User user);

    UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData);

	int checkCustomer(Integer productNo);

	//공지게시판 상세조회
	User selectUserByuserId(String userId);

    User updateMyPageByUser(User user);

//    배송지 추가
    int insertAddressByUser(Address address);

//  배송지 리스트 가져오기
    List<Address> selectAddressList(User user);
//  배송지 삭제
    int deleteAddress(int addressNo);

//    유저 회원 탈퇴
    int deleteUserByUserNo(int userNo);

    User getUserInfo(int userNo);

//     마이페이지 > 1:1문의 뷰
    List<MyPageQna> selectQna(MyPageQna qna);
//  문의 상세페이지, 문의번호
    MyPageQna selectQnaDetail(int answerNo);
}
