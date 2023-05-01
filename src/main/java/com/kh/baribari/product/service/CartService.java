package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.product.domain.Cart;

public interface CartService {
	/**
	 * 장바구니에 상품 추가
	 * @param Cart
	 * @return int
	 */
	int addToCart(Cart cart);

	/**
	 * 장바구니 목록
	 * @param int
	 * @return List<Cart>
	 */
	List<Cart> getCartList(int userNo);

	/**
	 * 장바구니에서 상품 제거
	 * @param Cart
	 * @return int
	 */
	int removeFromCart(Cart cart);
}
