package com.kh.baribari.user.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.domain.Role;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.repository.UserRepository;

@Repository
public class UserRepositoryLogic implements UserRepository {
    @Autowired
    private SqlSession session;


    @Override
    public User selectIdCheck(String id) {
        return session.selectOne("UserMapper.selectIdCheck",id);
    }

    @Override
    public User selectNickNameCheck(String nickName) {
        return session.selectOne("UserMapper.selectNickNameCheck",nickName);
    }

    @Override
    public int insertUserByUser(User user) {
        session.insert("UserMapper.insertUserByUser",user);
        return session.insert("UserMapper.insertUserByRole",user);
    }

    @Override
    public int insertUserBySeller(User user) {
        session.insert("UserMapper.insertUserBySeller",user);
        return session.insert("UserMapper.insertUserBySeller",user);
    }

    // 스프링시큐리티 user 로그인
    @Override
    public User findByUserId(String userId) {
        return session.selectOne("UserMapper.findByUserId",userId);
    }

    @Override
    public List<Role> findByUserNo(int userNo) {
        return session.selectList("UserMapper.findByUserNo",userNo);
    }

    @Override
    public UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData) {
        return session.selectOne("UserMapper.selectUserLevel", userUserMyPageData);
    }

	@Override
	public int checkCustomer(Integer productNo) {
		return session.selectOne("UserMapper.checkCustomer", productNo);
	}

	@Override //공지게시판 상세조회
	public User selectUserByuserId(String userId) {
		User user = session.selectOne("NoticeMapper.selectUserByuserId", userId);
		return user;
	}


//    마이페이지 - 유저 - 개인정보 수정
    @Override
    public User updateMyPageByUser(User user) {
        session.update("UserMapper.updateMyPageUser", user);
        return session.selectOne("UserMapper.selectUpdateByUser",user);
    }
}
