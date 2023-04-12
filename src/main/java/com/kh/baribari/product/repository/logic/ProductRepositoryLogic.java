package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;

@Repository
public class ProductRepositoryLogic implements ProductRepository {
	
	@Override
	public int getProductCount(SqlSession session, String productCategory) {
		int pCount = session.selectOne("ProductMapper.getProductCount", productCategory);
		return pCount;
	}
	
	@Override
	public List<Product> getProductList(SqlSession session, String productCategory, PageInfo pi) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		List<Product> pList = session.selectList("ProductMapper.getProductList", productCategory, rowBounds);
		return pList;
	}

}