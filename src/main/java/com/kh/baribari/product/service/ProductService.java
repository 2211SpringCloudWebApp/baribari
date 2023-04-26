package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;

public interface ProductService {
	/**
	 * 상품 갯수 가져오기
	 * @param String
	 * @return int
	 */
	public int getProductCount(String productCategory);

	/**
	 * 상품 목록 가져오기
	 * @param String
	 * @param PageInfo
	 * @return List<Product>
	 */
	public List<Product> getProductList(String productCategory, PageInfo pi);

	/**
	 * 상품 정보 가져오기
	 * @param int
	 * @return Product
	 */
	public Product getProductDetail(int productNo);

	/**
	 * 상품 분류에 따른 추천 상품 목록 가져오기
	 * @param String
	 * @return List<Product>
	 */
	public List<Product> getProductRecommendList(String productCategory);

	/**
	 * 헤더에서 검색한 상품 갯수 가져오기
	 * @param Search
	 * #return int
	 */
	public int getProductCountByKeyword(Search search);

	/**
	 * 헤더에서 검색한 상품 목록 가져오기
	 * @param PageInfo
	 * @param Search
	 * #return int
	 */
	public List<Product> getProductList(PageInfo pi, Search search);

	/**
	 * 인기 상품 목록 가져오기
	 * @return List<Product>
	 */
	public List<Product> getProductListBySales();

	/**
	 * 상품 등록
	 * @param Product
	 * @return int
	 */
	public int registerProduct(Product product);

}
