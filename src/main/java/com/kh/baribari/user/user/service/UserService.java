package com.kh.baribari.user.user.service;

import com.kh.baribari.user.user.domain.User;

public interface UserService {
    User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);

    int insertUserByUser(User user);

    int insertUserBySeller(User user);
}
