package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Notice;

public interface NoticeRepository {

	/**
	 * 게시판 목록 조회 Repository
	 * @param session
	 * @param pi
	 * @return
	 */
	List<Notice> selectNoticeList(SqlSession session, PageInfo pi);

	/**
	 * 게시판 전체 게시물 개수 Store
	 * @param session
	 * @return
	 */
	public int noticeListCount(SqlSession session);

}
