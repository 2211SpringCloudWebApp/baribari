package com.kh.baribari.user.service.logic;

import com.kh.baribari.user.repository.UserRepository;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository uRepository;

    @Override
    public User selectIdCheck(String id) {
        return uRepository.selectIdCheck(id);
    }

    @Override
    public User selectNickNameCheck(String nickName) {
        return uRepository.selectNickNameCheck(nickName);
    }

    @Override
    public int insertUserByUser(User user) {
        return uRepository.insertUserByUser(user);
    }

    @Override
    public int insertUserBySeller(User user) {
        return uRepository.insertUserBySeller(user);
    }
}
