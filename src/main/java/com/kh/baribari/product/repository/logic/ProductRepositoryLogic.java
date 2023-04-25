package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;

@Repository
public class ProductRepositoryLogic implements ProductRepository {
	@Override
	public int getProductCount(SqlSession session, String productCategory) {
		return session.selectOne("ProductMapper.getProductCount", productCategory);
	}
	
	@Override
	public List<Product> getProductList(SqlSession session, String productCategory, PageInfo pi) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("ProductMapper.getProductList", productCategory, rowBounds);
	}

	@Override
	public Product getProductDetail(SqlSession session, int productNo) {
		return session.selectOne("ProductMapper.getProductDetail", productNo);
	}

	@Override
	public List<Product> getProductRecommendList(SqlSession session, String productCategory) {
		return session.selectList("ProductMapper.getProductRecommendList", productCategory);
	}

	@Override
	public int getProductCountByKeyword(SqlSession session, Search search) {
		return session.selectOne("ProductMapper.getProductCountByKeyword", search);
	}

	@Override
	public List<Product> getProductList(SqlSession session, PageInfo pi, Search search) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("ProductMapper.getProductListByKeyword", search, rowBounds);
	}
}