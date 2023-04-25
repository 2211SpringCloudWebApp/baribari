package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
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
    	return pRepository.getProductCount(session, productCategory);
    }

	@Override
	public List<Product> getProductList(String productCategory, PageInfo pi) {
		return pRepository.getProductList(session, productCategory, pi);
	}

	@Override
	public Product getProductDetail(int productNo) {
		return pRepository.getProductDetail(session ,productNo);
	}

	@Override
	public List<Product> getProductRecommendList(String productCategory) {
		return pRepository.getProductRecommendList(session, productCategory);
	}

	@Override
	public int getProductCountByKeyword(Search search) {
		return pRepository.getProductCountByKeyword(session, search);
	}

	@Override
	public List<Product> getProductList(PageInfo pi, Search search) {
		return pRepository.getProductList(session, pi, search);
	}

}
