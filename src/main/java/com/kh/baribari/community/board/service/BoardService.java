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
	 * @return
	 */
	public int registerHashTag(HashTag hTag);

	/**
	 * 시퀀스넘버 미리 가져오기
	 * @return
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
	 * @return
	 */
	public int deleteHashTag(HashTag hTag);

	/**
	 * 사진등록
	 * @param pic
	 * @return
	 */
	public int registerPhoto(CommunityPIC pic);

}
