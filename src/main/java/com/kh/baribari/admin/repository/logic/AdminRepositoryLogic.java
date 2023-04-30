package com.kh.baribari.admin.repository.logic;

import com.kh.baribari.admin.domain.*;
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

    @Override
    public ReportList selectReportListByReportNo(int reportNo) {
        return session.selectOne("AdminMapper.selectReportListByReportNo",reportNo);
    }

    @Override
    public List<UserList> selectUserList() {
        return session.selectList("AdminMapper.selectUserList");
    }

    @Override
    public int updateUserBlock(int userNo) {
        return session.update("AdminMapper.updateUserBlock",userNo);
    }

    @Override
    public int updateUserUnBlock(int userNo) {
        return session.update("AdminMapper.updateUserUnBlock",userNo);
    }

    @Override
    public List<QnaList> selectQnaListByUser() {
        return session.selectList("AdminMapper.selectQnaListByAdmin");
    }

    @Override
    public QnaList selectQnaDetailByQnaNo(int qnaNo) {
        return session.selectOne("AdminMapper.selectQnaDetailByQnaNo",qnaNo);
    }

    @Override
    public int updateAnswerByAdmin(QnaList qnalist) {
        return session.update("AdminMapper.updateAnswerByAdmin",qnalist);
    }
}
