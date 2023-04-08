package com.kh.baribari.user.user.service.logic;

import com.kh.baribari.user.user.domain.User;
import com.kh.baribari.user.user.repository.UserRepository;
import com.kh.baribari.user.user.service.UserService;
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
}
