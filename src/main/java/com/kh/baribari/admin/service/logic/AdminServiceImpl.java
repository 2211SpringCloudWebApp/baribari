package com.kh.baribari.admin.service.logic;

import com.kh.baribari.admin.domain.CommunityList;
import com.kh.baribari.admin.domain.ProductList;
import com.kh.baribari.admin.domain.QnaList;
import com.kh.baribari.admin.domain.ReportList;
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
}
