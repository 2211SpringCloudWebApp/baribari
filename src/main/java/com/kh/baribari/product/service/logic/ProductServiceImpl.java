package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;
import com.kh.baribari.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository pRepository;
    @Autowired
    private SqlSession session;

	@Override
	public List<Product> getProductList(PageInfo pi) {
		List<Product> pList = pRepository.getProductList(session, pi);
		return pList;
	}

	@Override
	public int getProductCount() {
		int pCount = pRepository.getProductCount(session);
		return pCount;
	}

}
