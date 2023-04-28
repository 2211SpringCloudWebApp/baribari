package com.kh.baribari.user.repository.logic;

import java.util.List;

import com.kh.baribari.user.domain.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.baribari.user.repository.UserRepository;

@Repository
public class UserRepositoryLogic implements UserRepository {
    @Autowired
    private SqlSession session;


    @Override
    public User selectIdCheck(String id) {
        return session.selectOne("UserMapper.selectIdCheck",id);
    }

    @Override
    public User selectNickNameCheck(String nickName) {
        return session.selectOne("UserMapper.selectNickNameCheck",nickName);
    }

    @Override
    public int insertUserByUser(User user) {
        session.insert("UserMapper.insertUserByUser",user);
        return session.insert("UserMapper.insertUserByRole",user);
    }

    @Override
    public int insertUserBySeller(User user) {
        session.insert("UserMapper.insertUserBySeller",user);
        return session.insert("UserMapper.insertSellerByRole",user);
    }

    // 스프링시큐리티 user 로그인
    @Override
    public User findByUserId(String userId) {
        return session.selectOne("UserMapper.findByUserId",userId);
    }

    @Override
    public List<Role> findByUserNo(int userNo) {
        return session.selectList("UserMapper.findByUserNo",userNo);
    }

    @Override
    public UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData) {
        return session.selectOne("UserMapper.selectUserLevel", userUserMyPageData);
    }

	@Override
	public int checkCustomer(Integer productNo) {
		return session.selectOne("UserMapper.checkCustomer", productNo);
	}

	@Override //공지게시판 상세조회
	public User selectUserByuserId(String userId) {
		User user = session.selectOne("NoticeMapper.selectUserByuserId", userId);
		return user;
	}


//    마이페이지 - 유저 - 개인정보 수정
    @Override
    public User updateMyPageByUser(User user) {
        session.update("UserMapper.updateMyPageUser", user);
        return session.selectOne("UserMapper.selectUpdateByUser",user);
    }

    @Override
    public int insertAddressByUser(Address address) {
        return session.insert("UserMapper.insertAddressByUser",address);
    }

    @Override
    public List<Address> selectAddressList(User user) {
        return session.selectList("UserMapper.selectAddressList",user);
    }

	@Override
	public User getUserInfo(int userNo) {
		return session.selectOne("UserMapper.findUserInfoByUserNo", userNo);
	}

    @Override
    public List<MyPageQna> selectQna(MyPageQna qna) {
        return session.selectList("UserMapper.selectQnaByUserNo",qna);
    }

    @Override
    public int deleteAddress(int addressNo) {
        return session.delete("UserMapper.deleteAddress",addressNo);
    }

    @Override
    public int deleteUserByUser(int userNo) {
        return session.delete("UserMapper.deleteUser",userNo);
    }

    @Override
    public MyPageQna selectQnaDetail(int answerNo) {
        return session.selectOne("UserMapper.selectQnaDetail", answerNo);
    }

    @Override
    public int qnaRemove(int qnaNo) {
        return session.delete("UserMapper.deleteQna",qnaNo);
    }

    @Override
    public int qnaModifySave(MyPageQna myPageQna) {
        return session.update("UserMapper.updateQna", myPageQna);
    }

    @Override
    public int qnaWrite(MyPageQna myPageQna) {
        return session.insert("UserMapper.qnaWrite",myPageQna);
    }

    @Override
    public List<MyPageQna> selectProductQna(User user) {
        return session.selectList("UserMapper.selectProductQna",user);
    }

    @Override
    public List<MyPageOrderList> selectOrderList(MyPageOrderList myPageOrderListParam) {
        return session.selectList("UserMapper.selectOrderList",myPageOrderListParam);
    }

    @Override
    public List<CartList> selectCartList(int userNo) {
        return session.selectList("UserMapper.selectCartList", userNo);
    }

    @Override
    public int cartCountUpDown(CartList cartList) {
        return session.update("UserMapper.updateCartCountUpDown",cartList);
    }

    @Override
    public int cartRemove(CartList cartList) {
        return session.delete("UserMapper.deleteCartRemove",cartList);
    }

    @Override
    public User selectModifyUser(int userNo) {
        return session.selectOne("UserMapper.selectModifyUser",userNo);
    }

    @Override
    public int updateProfilePic(User user) {
        return session.update("UserMapper.updateProfilePic",user);
    }

    @Override
    public List<Favorite> selectFavorite(int userNo) {
        return session.selectList("UserMapper.selectFavoriteList",userNo);
    }

    @Override
    public int deleteFavorite(Favorite favorite) {
        return session.delete("UserMapper.deleteFavorite",favorite);
    }
}
