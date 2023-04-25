package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.product.domain.Cart;
import com.kh.baribari.product.repository.CartRepository;
import com.kh.baribari.product.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	private SqlSession session;
	@Autowired
	private CartRepository cRepository;
	
	@Override
	public int addToCart(Cart cart) {
		return cRepository.addToCart(session, cart);
	}

	@Override
	public List<Cart> getCartList(int userNo) {
		return cRepository.getCartList(session, userNo);
	}

	@Override
	public int removeFromCart(Cart cart) {
		return cRepository.removeFromCart(session, cart);
	}
}
