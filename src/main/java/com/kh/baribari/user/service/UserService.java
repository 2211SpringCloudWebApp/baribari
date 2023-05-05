package com.kh.baribari.user.service;

import java.util.List;

import com.kh.baribari.user.domain.*;

public interface UserService {
    User selectIdCheck(String id);

    User selectNickNameCheck(String nickName);

    int insertUserByUser(User user);

    int insertUserBySeller(User user);

    UserMyPageData selectUserMyPageData(UserMyPageData userUserMyPageData);

	int checkCustomer(Integer productNo);

	//공지게시판 상세조회
	User selectUserByuserId(String userId);

    User updateMyPageByUser(User user);

//    배송지 추가
    int insertAddressByUser(Address address);

//  배송지 리스트 가져오기
    List<Address> selectAddressList(User user);
//  배송지 삭제
    int deleteAddress(int addressNo);

//    유저 회원 탈퇴
    int deleteUserByUserNo(int userNo);

    User getUserInfo(int userNo);

//     마이페이지 > 1:1문의 뷰
    List<MyPageQna> selectQna(MyPageQna qna);
//  문의 상세페이지, 문의번호
    MyPageQna selectQnaDetail(int answerNo);
//  문의 삭제
    int qnaRemove(int qnaNo);
//  문의 수정 저장
    int qnaModifySave(MyPageQna myPageQna);
//  문의사항 작성
    int qnaWrite(MyPageQna myPageQna);

//  상품 문의사항 뷰
    List<MyPageQna> selectProductQna(User user);

//    주문내역 조회
    List<MyPageOrderList> selectOrderList(MyPageOrderList myPageOrderListParam);
//  장바구니 리스트 뷰
    List<CartList> selectCartList(int userNo);
//  장바구니 카운트 UP, Down
    int cartCountUpDown(CartList cartList);
//  장바구니 삭제
    int cartRemove(CartList cartList);
//  유저 수정 페이지 데이터
    User selectModifyUser(int userNo);
//  유저 프로필 사진 저장
    int updateProfilePic(User user);
//  찜한 상품 뷰
    List<Favorite> selectFavorite(int userNo);
//  찜한 상품 삭제
    int deleteFavorite(Favorite favorite);
//  유저 아이디 찾기
    String findUserId(User user);
//  유저 비밀번호 찾기 / 체크
    int findUserPw(User user);
//  유저 비밀번호 번경
    int pwChange(User user);
//  팩다운 리스트
    List<MPCommunityList> selectPegDownList(User user);
//  내가쓴글 로딩
    List<MPCommunityList> selectMyWrite(User user);
//  내가쓴댓글 로딩
    List<CommentList> selectCommentList(User user);
}
