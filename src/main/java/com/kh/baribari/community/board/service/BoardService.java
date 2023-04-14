package com.kh.baribari.community.board.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.domain.Community;

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
	public List<Community> getBoardListAll(PageInfo pi);

	/**
	 * 게시글 좋아요 수 조회
	 * @param boardNo
	 * @return int
	 */
	public int getListCount(int boardNo);

}
