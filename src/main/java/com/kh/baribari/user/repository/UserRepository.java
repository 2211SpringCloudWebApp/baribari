package com.kh.baribari.user.repository;

import java.util.List;

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

    User updateMyPageByUser(User user);
}
