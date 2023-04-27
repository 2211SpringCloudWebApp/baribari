package com.kh.baribari.community.board.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.domain.Community;
import com.kh.baribari.community.domain.CommunityPIC;
import com.kh.baribari.community.domain.HashTag;

public interface BoardService {

	/**
	 * 자유게시판 전체 게시글 수 조회
	 * @return int
	 */
	public int getBoardCount();

	/**
	 * 자유게시판 전체 게시글 목록 조회
	 * @param pi
	 * @return List<Community>
	 */
	public List<Community> getBoardListAll(PageInfo pi, int category);

	/**
	 * 게시글 좋아요 수 조회
	 * @param boardNo
	 * @return int
	 */
	public int getListCount(int boardNo);

	/**
	 * 헤시태그를 가져옴
	 * @return List<HashTag>
	 */
	public List<HashTag> getHashTag(int boardNo);

	/**
	 * 해시태그 등록
	 * @param hTag
	 * @return int
	 */
	public int registerHashTag(HashTag hTag);

	/**
	 * 시퀀스넘버 미리 가져오기
	 * @return int
	 */
	public int getSEQ();

	/**
	 * 게시글 등록
	 * @param commu
	 * @return int
	 */
	public int boardRegister(Community commu);

	/**
	 * 해시태그 삭제
	 * @param hTag
	 * @return int
	 */
	public int deleteHashTag(HashTag hTag);

	/**
	 * 사진등록
	 * @param pic
	 * @return int
	 */
	public int registerPhoto(CommunityPIC pic);
	
	/**
	 * 게시글 삭제
	 * @param boardNo
	 * @return int
	 */
	public int boardDelete(int boardNo);
	
	/**
	 * 사진 삭제
	 * @param boardNo
	 * @return int
	 */
	public int deletePhoto(int boardNo);

	/**
	 * 게시글 수정
	 * @param commu
	 * @return
	 */
	public int boardUpdate(Community commu);
	
	/**
	 * 게시글 조회
	 * @param boardNo
	 * @return Community
	 */
	public Community getBoardOne(int boardNo);

	/**
	 * 사진 리스트 가져오기
	 * @param boardNo
	 * @return CommunityPIC
	 */
	public CommunityPIC getPhoto(Integer boardNo);
}
