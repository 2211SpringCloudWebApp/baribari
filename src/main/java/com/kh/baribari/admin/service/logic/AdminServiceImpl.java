package com.kh.baribari.admin.service.logic;

import com.kh.baribari.admin.domain.*;
import com.kh.baribari.admin.repository.AdminRepository;
import com.kh.baribari.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminRepository aRepository;

    @Override
    public List<ReportList> selectReportList() {
        return aRepository.selectReportList();
    }

    @Override
    public List<CommunityList> selectCommunityList() {
        return aRepository.selectCommunityList();
    }

    @Override
    public List<QnaList> selectQnaList() {
        return aRepository.selectQnaList();
    }

    @Override
    public List<ProductList> selectProductList() {
        return aRepository.selectProductList();
    }

    @Override
    public ReportList selectReportListByReportNo(int reportNo) {
        return aRepository.selectReportListByReportNo(reportNo);
    }

    @Override
    public List<UserList> selectUserList() {
        return aRepository.selectUserList();
    }

    @Override
    public int updateUserBlock(int userNo) {
        return aRepository.updateUserBlock(userNo);
    }

    @Override
    public int updateUserUnBlock(int userNo) {
        return aRepository.updateUserUnBlock(userNo);
    }

    @Override
    public List<QnaList> selectQnaListByUser() {
        return aRepository.selectQnaListByUser();
    }

    @Override
    public QnaList selectQnaDetailByQnaNo(int qnaNo) {
        return aRepository.selectQnaDetailByQnaNo(qnaNo);
    }

    @Override
    public int updateAnswerByAdmin(QnaList qnalist) {
        return aRepository.updateAnswerByAdmin(qnalist);
    }
}
