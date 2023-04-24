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
		int result = session.insert("CartMapper.addToCart", cart);
		return result;
	}

	@Override
	public List<Cart> getCartList(SqlSession session, int userNo) {
		List<Cart> cList = session.selectList("CartMapper.getCartList", userNo);
		return cList;
	}

	@Override
	public int removeFromCart(SqlSession session, Cart cart) {
		int result = session.delete("CartMapper.removeFromCart", cart);
		return result;
	}

}
