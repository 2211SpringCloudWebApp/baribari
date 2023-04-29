package com.kh.baribari.admin.repository.logic;

import com.kh.baribari.admin.domain.CommunityList;
import com.kh.baribari.admin.domain.ProductList;
import com.kh.baribari.admin.domain.QnaList;
import com.kh.baribari.admin.domain.ReportList;
import com.kh.baribari.admin.repository.AdminRepository;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepositoryLogic implements AdminRepository {

    @Autowired
    private SqlSession session;

    @Override
    public List<ReportList> selectReportList() {
        return session.selectList("AdminMapper.selectReportList");
    }

    @Override
    public List<CommunityList> selectCommunityList() {
        return session.selectList("AdminMapper.selectCommunityList");
    }

    @Override
    public List<QnaList> selectQnaList() {
        return session.selectList("AdminMapper.selectQnaList");
    }

    @Override
    public List<ProductList> selectProductList() {
        return session.selectList("AdminMapper.selectProductList");
    }
}
