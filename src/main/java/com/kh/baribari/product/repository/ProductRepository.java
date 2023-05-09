package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.user.domain.Favorite;

public interface ProductRepository {
	/**
	 * 상품 갯수 가져오기
	 * @param SqlSession
	 * @param String
	 * @return int
	 */
	int getProductCount(SqlSession session, String productCategory);

	/**
	 * 상품 목록 가져오기
	 * @param SqlSession
	 * @param String
	 * @param PageInfo
	 * @return List<Product>
	 */
	List<Product> getProductList(SqlSession session, String productCategory, PageInfo pi);

	/**
	 * 상품 정보 가져오기
	 * @param SqlSession
	 * @param int
	 * @return Product
	 */
	Product getProductDetail(SqlSession session, int productNo);

	/**
	 * 상품 분류에 따른 추천 상품 목록 가져오기
	 * @param SqlSession
	 * @param String
	 * @return List<Product>
	 */
	List<Product> getProductRecommendList(SqlSession session, String productCategory);

	/**
	 * 헤더에서 검색한 상품 갯수 가져오기
	 * @param SqlSession
	 * @param Search
	 * #return int
	 */
	int getProductCountByKeyword(SqlSession session, Search search);
	
	/**
	 * 헤더에서 검색한 상품 갯수 가져오기
	 * @param SqlSession
	 * @param PageInfo
	 * @param Search
	 * @return List<Product>
	 */
	List<Product> getProductList(SqlSession session, PageInfo pi, Search search);

	/**
	 * 인기 상품 목록 가져오기
	 * @param SqlSession
	 * @return List<Product>
	 */
	List<Product> getProductListBySales(SqlSession session);

	/**
	 * 상품 등록
	 * @param SqlSession
	 * @param Product
	 * @return int
	 */
	int registerProduct(SqlSession session, Product product);

	/**
	 * MD추천 상품 목록 가져오기
	 * @param SqlSession
	 * @return List<Product>
	 */
	List<Product> getMdRecommendProductList(SqlSession session);

	/**
	 * MD추천 상품 추가/제거하기
	 * @param SqlSession
	 * @param Product
	 * @return int
	 */
	int mdRecommend(SqlSession session, Product product);

	/**
	 * 상품 삭제하기
	 * @param SqlSession
	 * @param int
	 * @return int
	 */
	int deleteProduct(SqlSession session, int productNo);

	/**
	 * 찜하기 추가하기
	 * @param SqlSession
	 * @param Favorite
	 * @return int
	 */
	int addScrap(SqlSession session, Favorite favorite);
	
	/**
	 * 찜하기 제거하기
	 * @param SqlSession
	 * @param Favorite
	 * @return int
	 */
	int removeScrap(SqlSession session, Favorite favorite);

	/**
	 * 찜하기 목록 가져오기
	 * @param userNo 
	 * @param SqlSession
	 * @return int
	 */
	List<Favorite> getFavoriteList(SqlSession session, int userNo);
	
	/**
	 * 판매자가 판매하고 있는 상품 목록
	 * @param SqlSession
	 * @param int
	 * @return List<Product>
	 */
	List<Product> getProductListBySeller(SqlSession session, int userNo);

	/**
	 * 상품 수정하기
	 * @param SqlSession
	 * @param Product
	 * @return int
	 */
	int modifyProduct(SqlSession session, Product product);

	int getFavorite(SqlSession session, Favorite favorite);

	int addFavorite(SqlSession session, Favorite favorite);

	int removeFavorite(SqlSession session, Favorite favorite);
}
	
