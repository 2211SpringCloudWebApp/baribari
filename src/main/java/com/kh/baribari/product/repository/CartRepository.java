package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.product.domain.Cart;

public interface CartRepository {

	/**
	 * 장바구니에 상품 추가
	 * @param SqlSession
	 * @param Cart
	 * @return int
	 */
	public int addToCart(SqlSession session, Cart cart);

	/**
	 * 장바구니 목록
	 * @param SqlSession
	 * @param int
	 * @return int
	 */
	public List<Cart> getCartList(SqlSession session, int userNo);

	/**
	 * 장바구니에서 상품 제거
	 * @param SqlSession
	 * @param Cart
	 * @return int
	 */
	public int removeFromCart(SqlSession session, Cart cart);
}
