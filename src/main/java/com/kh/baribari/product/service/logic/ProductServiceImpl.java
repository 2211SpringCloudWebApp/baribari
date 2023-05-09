package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;
import com.kh.baribari.product.service.ProductService;
import com.kh.baribari.user.domain.Favorite;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository pRepository;
	@Autowired
	private SqlSession session;

	@Override
	public int getProductCount(String productCategory) {
		return pRepository.getProductCount(session, productCategory);
	}

	@Override
	public List<Product> getProductList(String productCategory, PageInfo pi) {
		return pRepository.getProductList(session, productCategory, pi);
	}

	@Override
	public Product getProductDetail(int productNo) {
		return pRepository.getProductDetail(session, productNo);
	}

	@Override
	public List<Product> getProductRecommendList(String productCategory) {
		return pRepository.getProductRecommendList(session, productCategory);
	}

	@Override
	public int getProductCountByKeyword(Search search) {
		return pRepository.getProductCountByKeyword(session, search);
	}

	@Override
	public List<Product> getProductList(PageInfo pi, Search search) {
		return pRepository.getProductList(session, pi, search);
	}

	@Override
	public List<Product> getProductListBySales() {
		return pRepository.getProductListBySales(session);
	}

	@Override
	public int registerProduct(Product product) {
		return pRepository.registerProduct(session, product);
	}

	@Override
	public List<Product> getMdRecommendProductList() {
		return pRepository.getMdRecommendProductList(session);
	}

	@Override
	public int mdRecommend(Product product) {
		return pRepository.mdRecommend(session, product);
	}

	@Override
	public int deleteProduct(int productNo) {
		return pRepository.deleteProduct(session, productNo);
	}

	@Override
	public int addScrap(Favorite favorite) {
		return pRepository.addScrap(session, favorite);
	}
	
	@Override
	public int removeScrap(Favorite favorite) {
		return pRepository.removeScrap(session, favorite);
	}

	@Override
	public List<Favorite> getFavoriteList(int userNo) {
		return pRepository.getFavoriteList(session, userNo);
	}

	@Override
	public List<Product> getProductListBySeller(int userNo) {
		return pRepository.getProductListBySeller(session, userNo);
	}

	@Override
	public int modifyProduct(Product product) {
		return pRepository.modifyProduct(session, product);
	}
}
