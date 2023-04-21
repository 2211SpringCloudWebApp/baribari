package com.kh.baribari.product.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
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
	 * 게시판 전체 게시물 개수 Repository
	 * @param session
	 * @return
	 */
	public int noticeListCount(SqlSession session);

	/**
	 * 조회수 증가 Repository
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	public int updateViewCount(SqlSession session, int noticeNo);

	/**
	 * 게시판 상세 Repository
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	public Notice selectOneByNo(SqlSession session, int noticeNo);

	/**
	 * 검색 목록 Repository
	 * @param session
	 * @param search
	 * @return
	 */
	public int noticeSearch(SqlSession session, Search search);

	/**
	 * 검색 Repositry
	 * @param session
	 * @param pi
	 * @param search
	 * @return
	 */
	public List<Notice> selectListByKeyword(SqlSession session, PageInfo pi, Search search);

	/**
	 * 게시판 글쓰기 Repositry
	 * @param session
	 * @param notice
	 * @return
	 */
	public int writeNotice(SqlSession session, Notice notice);

	/**
	 * 게시판 수정 뷰 Repositry
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	Notice noticeModifyView(SqlSession session, int noticeNo);

	/**
	 * 게시판 수정
	 * @param session
	 * @param notice
	 * @return
	 */
	public int modifyNotice(SqlSession session, Notice notice);

	/**
	 * 게시판 삭제
	 * @param session
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(SqlSession session, int noticeNo);

}
