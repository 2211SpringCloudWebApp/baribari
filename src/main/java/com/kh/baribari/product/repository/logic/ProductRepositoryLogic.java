package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Product;
import com.kh.baribari.product.repository.ProductRepository;
import com.kh.baribari.user.domain.Favorite;

@Repository
public class ProductRepositoryLogic implements ProductRepository {
	@Override
	public int getProductCount(SqlSession session, String productCategory) {
		return session.selectOne("ProductMapper.getProductCount", productCategory);
	}
	
	@Override
	public List<Product> getProductList(SqlSession session, String productCategory, PageInfo pi) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("ProductMapper.getProductList", productCategory, rowBounds);
	}

	@Override
	public Product getProductDetail(SqlSession session, int productNo) {
		return session.selectOne("ProductMapper.getProductDetail", productNo);
	}

	@Override
	public List<Product> getProductRecommendList(SqlSession session, String productCategory) {
		return session.selectList("ProductMapper.getProductRecommendList", productCategory);
	}

	@Override
	public int getProductCountByKeyword(SqlSession session, Search search) {
		return session.selectOne("ProductMapper.getProductCountByKeyword", search);
	}

	@Override
	public List<Product> getProductList(SqlSession session, PageInfo pi, Search search) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		return session.selectList("ProductMapper.getProductListByKeyword", search, rowBounds);
	}

	@Override
	public List<Product> getProductListBySales(SqlSession session) {
		return session.selectList("ProductMapper.getProductListBySales");
	}

	@Override
	@Transactional
	public int registerProduct(SqlSession session, Product product) {
		// PRODUCT_TBL에 저장
	    int result1 = session.insert("ProductMapper.insertProduct", product);
	    int result2 = 0;
	    // 사진이 있을 경우 PRODUCT_PIC_TBL에 저장
	    if (product.getProductPic1() != null) {
	    	result2 = session.insert("ProductMapper.insertProductPic", product);
	    }
	    // 성공 시 1 또는 2 반환
	    return result1 + result2;
	}

	@Override
	public List<Product> getMdRecommendProductList(SqlSession session) {
		return session.selectList("ProductMapper.getMdRecommendProductList");
	}

	@Override
	public int mdRecommend(SqlSession session, Product product) {
		return session.update("ProductMapper.mdRecommend", product);
	}

	@Override
	public int deleteProduct(SqlSession session, int productNo) {
		return session.delete("ProductMapper.deleteProduct", productNo);
	}

	@Override
	public int addScrap(SqlSession session, Favorite favorite) {
		return session.insert("ProductMapper.addScrap", favorite);
	}
	
	@Override
	public int removeScrap(SqlSession session, Favorite favorite) {
		return session.delete("ProductMapper.removeScrap", favorite);
	}

	@Override
	public List<Favorite> getFavoriteList(SqlSession session, int userNo) {
		return session.selectList("ProductMapper.getFavoriteList", userNo);
	}

	@Override
	public List<Product> getProductListBySeller(SqlSession session, int userNo) {
		return session.selectList("ProductMapper.getProductListBySeller", userNo);
	}

	@Override
	@Transactional
	public int modifyProduct(SqlSession session, Product product) {
		// PRODUCT_TBL에 저장
	    int result1 = session.update("ProductMapper.modifyProduct", product);
	    int result2 = 0;
	    // 사진이 있을 경우 PRODUCT_PIC_TBL에 저장
	    if (product.getProductPic1() != null) {
	    	result2 = session.update("ProductMapper.modifyProductPic", product);
	    }
	    // 성공 시 1 또는 2 반환
	    return result1 + result2;
	}

	@Override
	public int getFavorite(SqlSession session, Favorite favorite) {
		return session.selectOne("ProductMapper.getFavorite", favorite);
	}

	@Override
	public int addFavorite(SqlSession session, Favorite favorite) {
		return session.insert("ProductMapper.addFavorite", favorite);
	}

	@Override
	public int removeFavorite(SqlSession session, Favorite favorite) {
		System.out.println(favorite);
		return session.delete("ProductMapper.removeFavorite", favorite);
	}
}