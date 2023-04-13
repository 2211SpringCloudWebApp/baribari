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
    public int getProductCount(String productCategory) {
    	int pCount = pRepository.getProductCount(session, productCategory);
    	return pCount;
    }

	@Override
	public List<Product> getProductList(String productCategory, PageInfo pi) {
		List<Product> pList = pRepository.getProductList(session, productCategory, pi);
		return pList;
	}

	@Override
	public Product getProductDetail(int productNo) {
		Product product = pRepository.getProductDetail(session ,productNo);
		return product;
	}

	@Override
	public List<Product> getProductRecommendList(String productCategory) {
		List<Product> pList = pRepository.getProductRecommendList(session, productCategory);
		return pList;
	}

}
