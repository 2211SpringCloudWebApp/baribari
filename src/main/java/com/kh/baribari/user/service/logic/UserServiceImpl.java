package com.kh.baribari.user.service.logic;

import com.kh.baribari.user.domain.UserMyPageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.repository.UserRepository;
import com.kh.baribari.user.service.UserService;

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

    @Override
    public UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData) {
        return uRepository.selectUserMyPageData(userUserMyPageData);
    }

	@Override
	public int checkCustomer(Integer productNo) {
		return uRepository.checkCustomer(productNo);
	}

	@Override
	public User selectUserByuserId(String userId) {
		return uRepository.selectUserByuserId(userId);
	}

//    유저마이페이지 - 개인정보 수정
    @Override
    public User updateMyPageByUser(User user) {
        return uRepository.updateMyPageByUser(user);
    }
}
