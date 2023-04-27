package com.kh.baribari.product.repository;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.product.domain.Order;

public interface OrderRepository {

	public int setOrder(SqlSession session, Order order);

}
