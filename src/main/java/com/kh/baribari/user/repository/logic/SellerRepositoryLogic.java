package com.kh.baribari.user.repository.logic;

import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.repository.SellerRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SellerRepositoryLogic implements SellerRepository {

    @Autowired
    private SqlSession session;

    @Override
    public UserMyPageData selectMyPageSeller(UserMyPageData userUserMyPageData) {
        return session.selectOne("UserMapper.selectMyPageSeller",userUserMyPageData);
    }
}
