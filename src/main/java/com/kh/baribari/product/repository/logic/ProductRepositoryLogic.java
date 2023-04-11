package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;

@Repository
public class ProductRepositoryLogic implements ProductRepository {
	
	@Override
	public List<Product> getProductList(SqlSession session) {
		List<Product> pList = session.selectList("ProductMapper.getProductList");
		return pList;
	}

	@Override
	public int getProductCount(SqlSession session) {
		int pCount = session.selectOne("ProductMapper.getProductCount");
		return pCount;
	}

}
