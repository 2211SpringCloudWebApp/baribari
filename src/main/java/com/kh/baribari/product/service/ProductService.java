package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.product.domain.Product;

public interface ProductService {

	/**
	 * 상품 목록 가져오기
	 * @return List<Product>
	 */
	public List<Product> selectProductList();

}
