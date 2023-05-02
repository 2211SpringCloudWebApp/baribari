package com.kh.baribari.community.board.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.CommunityPIC;
import com.kh.baribari.community.domain.HashTag;

public interface BoardRepository {

	/**
	 * 자유게시판 전체 게시글 수 조회
	 * @param session
	 * @return int
	 */
	public int getBoardCount(SqlSession session, int category);

	/**
	 * 자유게시판 게시글 전체 목록 조회
	 * @param session
	 * @param pi
	 * @return List<Community>
	 */
	public List<Community> getBoardListAll(SqlSession session, PageInfo pi, Community comm);

	/**
	 * 게시글 좋아요 수 조회
	 * @param boardNo
	 * @return int
	 */
	public int getListCount(SqlSession session, int boardNo);

	/**
	 * 시퀀스넘버 미리 가져오기
	 * @return
	 */
	public int getSEQ(SqlSession session);

	/**
	 * 게시글 등록
	 * @param session
	 * @param commu
	 * @return
	 */
	public int boardRegister(SqlSession session, Community commu);

	/**
	 * 사진등록
	 * @param session
	 * @param pic
	 * @return
	 */
	public int registerPhoto(SqlSession session, CommunityPIC pic);

	/**
	 * 게시글 삭제
	 * @param session
	 * @param boardNo
	 * @return
	 */
	public int boardDelete(SqlSession session, int boardNo);

	/**
	 * 사진 삭제
	 * @param session
	 * @param boardNo
	 * @return
	 */
	public int deletePhoto(SqlSession session, int boardNo);

	/**
	 * 게시글 수정
	 * @param session
	 * @param commu
	 * @return
	 */
	public int boardUpdate(SqlSession session, Community commu);

	/**
	 * 게시글 하나 조회
	 * @param session
	 * @param boardNo
	 * @return Community
	 */
	public Community getBoardOne(SqlSession session, int boardNo);

	/**
	 * 사진 가져오기
	 * @param session
	 * @param boardNo
	 * @return CommunityPIC
	 */
	public CommunityPIC getPhoto(SqlSession session, Integer boardNo);

	/**
	 * 유저 포인트 올리기
	 * @param session
	 * @param userNo
	 * @return
	 */
	public int userPointUp(SqlSession session, int userNo);

	/**
	 * 조회수 증가
	 * @param session
	 * @param boardNo
	 */
	public void plusViewCount(SqlSession session, Integer boardNo);

}
