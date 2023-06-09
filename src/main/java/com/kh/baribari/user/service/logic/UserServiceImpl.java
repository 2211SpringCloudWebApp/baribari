package com.kh.baribari.user.service.logic;

import java.util.List;

import com.kh.baribari.user.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.user.repository.UserRepository;
import com.kh.baribari.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository uRepository;

    @Override
    public User selectIdCheck(String id) {
        return uRepository.selectIdCheck(id);
    }

    @Override
    public User selectNickNameCheck(String nickName) {
        return uRepository.selectNickNameCheck(nickName);
    }

    @Override
    public int insertUserByUser(User user) {
        return uRepository.insertUserByUser(user);
    }

    @Override
    public int insertUserBySeller(User user) {
        return uRepository.insertUserBySeller(user);
    }

    @Override
    public UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData) {
        return uRepository.selectUserMyPageData(userUserMyPageData);
    }

    @Override
    public int checkCustomer(Integer productNo) {
        return uRepository.checkCustomer(productNo);
    }

    @Override
    public User selectUserByuserId(String userId) {
        return uRepository.selectUserByuserId(userId);
    }

    //    유저마이페이지 - 개인정보 수정
    @Override
    public User updateMyPageByUser(User user) {
        return uRepository.updateMyPageByUser(user);
    }

    @Override
    public int insertAddressByUser(Address address) {
        return uRepository.insertAddressByUser(address);
    }

    //    배송지 리스트 가져오기
    @Override
    public List<Address> selectAddressList(User user) {
        return uRepository.selectAddressList(user);
    }

    @Override
    public User getUserInfo(int userNo) {
        return uRepository.getUserInfo(userNo);
    }

    @Override
    public List<MyPageQna> selectQna(MyPageQna qna) {
        return uRepository.selectQna(qna);
    }

    @Override
    public MyPageQna selectQnaDetail(int answerNo) {
        return uRepository.selectQnaDetail(answerNo);
    }

    @Override
    public int qnaRemove(int qnaNo) {
        return uRepository.qnaRemove(qnaNo);
    }

    @Override
    public int qnaModifySave(MyPageQna myPageQna) {
        return uRepository.qnaModifySave(myPageQna);
    }

    @Override
    public int qnaWrite(MyPageQna myPageQna) {
        return uRepository.qnaWrite(myPageQna);
    }

    @Override
    public List<MyPageQna> selectProductQna(User user) {
        return uRepository.selectProductQna(user);
    }

    @Override
    public List<MyPageOrderList> selectOrderList(MyPageOrderList myPageOrderListParam) {
        return uRepository.selectOrderList(myPageOrderListParam);
    }

    @Override
    public List<CartList> selectCartList(int userNo) {
        return uRepository.selectCartList(userNo);
    }

    @Override
    public int cartCountUpDown(CartList cartList) {
        return uRepository.cartCountUpDown(cartList);
    }

    @Override
    public int cartRemove(CartList cartList) {
        return uRepository.cartRemove(cartList);
    }

    @Override
    public User selectModifyUser(int userNo) {
        return uRepository.selectModifyUser(userNo);
    }

    @Override
    public int updateProfilePic(User user) {
        return uRepository.updateProfilePic(user);
    }

    @Override
    public List<Favorite> selectFavorite(int userNo) {
        return uRepository.selectFavorite(userNo);
    }

    @Override
    public int deleteFavorite(Favorite favorite) {
        return uRepository.deleteFavorite(favorite);
    }

    @Override
    public String findUserId(User user) {
        return uRepository.findUserId(user);
    }

    @Override
    public int findUserPw(User user) {
        return uRepository.findUserPw(user);
    }

    @Override
    public int pwChange(User user) {
        return uRepository.changePw(user);
    }

    @Override
    public List<MPCommunityList> selectPegDownList(User user) {
        return uRepository.selectPegDownList(user);
    }

    @Override
    public List<MPCommunityList> selectMyWrite(User user) {
        return uRepository.selectMyWrite(user);
    }

    @Override
    public List<CommentList> selectCommentList(User user) {
        return uRepository.selectCommentList(user);
    }

    @Override
    public List<MyPageReportList> selectReportList(User user) {
        return uRepository.selectReportList(user);
    }

    //  배송지 삭제
    @Override
    public int deleteAddress(int addressNo) {
        return uRepository.deleteAddress(addressNo);
    }

    //    유저 회원탈퇴
    @Override
    public int deleteUserByUserNo(int userNo) {
        return uRepository.deleteUserByUser(userNo);
    }
}
