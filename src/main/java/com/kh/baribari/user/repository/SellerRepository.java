package com.kh.baribari.user.repository;

import com.kh.baribari.user.domain.UserMyPageData;

public interface SellerRepository {
    UserMyPageData selectMyPageSeller(UserMyPageData userUserMyPageData);
}
