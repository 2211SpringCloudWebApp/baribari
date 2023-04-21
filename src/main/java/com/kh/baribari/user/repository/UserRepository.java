package com.kh.baribari.user.repository;

import java.util.List;

import com.kh.baribari.user.domain.Address;
import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.domain.Role;
import com.kh.baribari.user.domain.User;

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
}
