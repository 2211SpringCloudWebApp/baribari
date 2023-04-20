package com.kh.baribari.user.service;

import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.domain.User;

public interface UserService {
    User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);

    int insertUserByUser(User user);

    int insertUserBySeller(User user);

    UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData);

	int checkCustomer(Integer productNo);

    User updateMyPageByUser(User user);
}
