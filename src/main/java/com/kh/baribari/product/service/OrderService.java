package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.product.domain.Order;

public interface OrderService {

	int setOrder(Order order);


	/**
	 * 판매자가 판매하고 있는 상품의 주문 목록
	 * @param int
	 * @return List<Product>
	 */
	List<Order> getOrderList(int userNo);

}
