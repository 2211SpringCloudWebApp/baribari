package com.kh.baribari.user.service.logic;

import com.kh.baribari.user.domain.UserMyPageData;
import com.kh.baribari.user.repository.SellerRepository;
import com.kh.baribari.user.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerRepository sRepository;

    @Override
    public UserMyPageData selectMyPageSeller(UserMyPageData userUserMyPageData) {
        return sRepository.selectMyPageSeller(userUserMyPageData);
    }
}
