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
	public int getBoardCount(SqlSession session);

	/**
	 * 자유게시판 게시글 전체 목록 조회
	 * @param session
	 * @param pi
	 * @return List<Community>
	 */
	public List<Community> getBoardListAll(SqlSession session, PageInfo pi, int category);

	/**
	 * 게시글 좋아요 수 조회
	 * @param boardNo
	 * @return int
	 */
	public int getListCount(SqlSession session, int boardNo);

	/**
	 * 해시태그 가져오기
	 * @param communityNo
	 * @return List<HashTag>
	 */
	public List<HashTag> getHashTag(SqlSession session,int boardNo);

	/**
	 * 해시태그 등록
	 * @param session
	 * @param hTag
	 * @return
	 */
	public int registerHashTag(SqlSession session, HashTag hTag);

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
	 * 해시태그 삭제
	 * @param session
	 * @param hTag
	 * @return
	 */
	public int deleteHashTag(SqlSession session, HashTag hTag);

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

}
