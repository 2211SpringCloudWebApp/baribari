package com.kh.baribari.user.user.repository.logic;

import com.kh.baribari.user.user.domain.User;
import com.kh.baribari.user.user.repository.UserRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;

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
        return session.insert("UserMapper.insertUserByUser",user);
    }

    @Override
    public int insertUserBySeller(User user) {
        return session.insert("UserMapper.insertUserBySeller",user);
    }

    @Override
    public User findByUserId(String userId) {
        return session.selectOne("UserMapper.findByUserId",userId);
    }
}
