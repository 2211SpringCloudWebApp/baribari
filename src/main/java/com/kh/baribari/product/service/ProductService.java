package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.user.domain.Favorite;

public interface ProductService {
	/**
	 * 상품 갯수 가져오기
	 * @param String
	 * @return int
	 */
	int getProductCount(String productCategory);

	/**
	 * 상품 목록 가져오기
	 * @param String
	 * @param PageInfo
	 * @return List<Product>
	 */
	List<Product> getProductList(String productCategory, PageInfo pi);

	/**
	 * 상품 정보 가져오기
	 * @param int
	 * @return Product
	 */
	Product getProductDetail(int productNo);

	/**
	 * 상품 분류에 따른 추천 상품 목록 가져오기
	 * @param String
	 * @return List<Product>
	 */
	List<Product> getProductRecommendList(String productCategory);

	/**
	 * 헤더에서 검색한 상품 갯수 가져오기
	 * @param Search
	 * #return int
	 */
	int getProductCountByKeyword(Search search);

	/**
	 * 헤더에서 검색한 상품 목록 가져오기
	 * @param PageInfo
	 * @param Search
	 * #return int
	 */
	List<Product> getProductList(PageInfo pi, Search search);

	/**
	 * 인기 상품 목록 가져오기
	 * @return List<Product>
	 */
	List<Product> getProductListBySales();

	/**
	 * 상품 등록
	 * @param Product
	 * @return int
	 */
	int registerProduct(Product product);

	/**
	 * MD추천 상품 목록 가져오기
	 * @return List<Product>
	 */
	List<Product> getMdRecommendProductList();

	/**
	 * MD추천 상품 추가/제거하기
	 * @param Product
	 * @return int
	 */
	int mdRecommend(Product product);

	/**
	 * 상품 삭제하기
	 * @param int
	 * @return int
	 */
	int deleteProduct(int productNo);

	/**
	 * 찜하기 추가/제거하기
	 * @param Favorite
	 * @return int
	 */
	int addScrap(Favorite favorite);
	
	/**
	 * 찜하기 추가/제거하기
	 * @param Favorite
	 * @return int
	 */
	int removeScrap(Favorite favorite);

	/**
	 * 찜하기 목록
	 * @return List<Favorite>
	 */
	List<Favorite> getFavoriteList();
	
	/**
	 * 판매자가 판매하고 있는 상품 목록
	 * @param int
	 * @return List<Product>
	 */
	List<Product> getProductListBySeller(int userNo);
}