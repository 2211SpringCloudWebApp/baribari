package com.kh.baribari.user.user.repository;

import com.kh.baribari.user.user.domain.User;

public interface UserRepository {
        User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);
}
