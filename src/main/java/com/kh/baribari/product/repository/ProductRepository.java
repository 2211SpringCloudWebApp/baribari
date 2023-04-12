package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Product;

public interface ProductRepository {

	/**
	 * 상품 목록 가져오기
	 * @param session
	 * @param PageInfo
	 * @return List<Product>
	 */
	public List<Product> getProductList(SqlSession session, PageInfo pi);

	/**
	 * 상품 갯수 가져오기
	 * @return int
	 */
	public int getProductCount(SqlSession session);
	
}
