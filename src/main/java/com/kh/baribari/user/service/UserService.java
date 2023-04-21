package com.kh.baribari.user.service;

import com.kh.baribari.user.domain.Address;
import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.domain.User;

import java.util.List;

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
}
