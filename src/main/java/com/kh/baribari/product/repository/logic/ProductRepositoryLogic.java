package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;

@Repository
public class ProductRepositoryLogic implements ProductRepository {
	
	@Override
	public List<Product> selectProductList(SqlSession session) {
		
		List<Product> pList = session.selectList("ProductMapper.selectProductList");
		
		return pList;
	}

}
