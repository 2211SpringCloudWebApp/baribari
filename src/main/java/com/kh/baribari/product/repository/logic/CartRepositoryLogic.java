package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.product.domain.Cart;
import com.kh.baribari.product.repository.CartRepository;

@Repository
public class CartRepositoryLogic implements CartRepository {

	@Override
	public int addToCart(SqlSession session, Cart cart) {
		return session.insert("CartMapper.addToCart", cart);
	}

	@Override
	public List<Cart> getCartList(SqlSession session, int userNo) {
		return session.selectList("CartMapper.getCartList", userNo);
	}

	@Override
	public int removeFromCart(SqlSession session, Cart cart) {
		return session.delete("CartMapper.removeFromCart", cart);
	}

}
