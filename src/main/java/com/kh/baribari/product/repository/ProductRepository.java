package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;

public interface ProductRepository {
	/**
	 * 상품 갯수 가져오기
	 * @param SqlSession
	 * @param String
	 * @return int
	 */
	public int getProductCount(SqlSession session, String productCategory);

	/**
	 * 상품 목록 가져오기
	 * @param SqlSession
	 * @param String
	 * @param PageInfo
	 * @return List<Product>
	 */
	public List<Product> getProductList(SqlSession session, String productCategory, PageInfo pi);

	/**
	 * 상품 정보 가져오기
	 * @param SqlSession
	 * @param int
	 * @return Product
	 */
	public Product getProductDetail(SqlSession session, int productNo);

	/**
	 * 상품 분류에 따른 추천 상품 목록 가져오기
	 * @param SqlSession
	 * @param String
	 * @return List<Product>
	 */
	public List<Product> getProductRecommendList(SqlSession session, String productCategory);

	/**
	 * 헤더에서 검색한 상품 갯수 가져오기
	 * @param SqlSession
	 * @param Search
	 * #return int
	 */
	public int getProductCountByKeyword(SqlSession session, Search search);
	
	/**
	 * 헤더에서 검색한 상품 갯수 가져오기
	 * @param SqlSession
	 * @param PageInfo
	 * @param Search
	 * @return List<Product>
	 */
	public List<Product> getProductList(SqlSession session, PageInfo pi, Search search);
}
