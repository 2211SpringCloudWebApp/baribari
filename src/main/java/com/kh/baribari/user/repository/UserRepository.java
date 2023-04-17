package com.kh.baribari.user.repository;

import com.kh.baribari.user.domain.Role;
import com.kh.baribari.user.domain.User;

import java.util.List;

public interface UserRepository {
        User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);

    int insertUserByUser(User user);

    int insertUserBySeller(User user);

    User findByUserId(String userId);

    List<Role> findByUserNo(int userNo);
}
