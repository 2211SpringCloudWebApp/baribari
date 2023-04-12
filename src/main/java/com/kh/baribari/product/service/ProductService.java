package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Product;

public interface ProductService {
	
	/**
	 * 상품 갯수 가져오기
	 * @param String
	 * @return int
	 */
	public int getProductCount(String productCategory);

	/**
	 * 상품 목록 가져오기
	 * @param String
	 * @param PageInfo
	 * @return List<Product>
	 */
	public List<Product> getProductList(String productCategory, PageInfo pi);

	/**
	 * 상품 정보 가져오기
	 * @param int
	 * @return Product
	 */
	public Product getProductDetail(int productNo);

}
