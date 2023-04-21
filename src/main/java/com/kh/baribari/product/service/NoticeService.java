package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.product.domain.Notice;

public interface NoticeService {

	/**
	 * 게시판 목록조회 Service
	 * @param pi
	 * @return
	 */
	public List<Notice> selectNoticeList(PageInfo pi);

	/**
	 * 게시판 게시물 전체 개수 Service 
	 * @return
	 */
	public int noticeListCount();

	/**
	 * 조회수 증가 Service
	 * @param noticeNo
	 * @return
	 */
	public int updateViewCount(int noticeNo);

	/**
	 * 게시판 상세 조회 Service
	 * @param noticeNo
	 * @return
	 */
	public Notice selectOneByNo(int noticeNo);

	/**
	 * 게시판 검색목록 Service
	 * @param search
	 * @return
	 */
	public int noticeSearch(Search search);

	/**
	 * 게시판 검색 Service
	 * @param pi
	 * @param search
	 * @return
	 */
	public List<Notice> selectListByKeyword(PageInfo pi, Search search);

	/**
	 * 게시판 글쓰기 Service 
	 * @param notice
	 * @return
	 */
	public int writeNotice(Notice notice);

	/**
	 * 게시판 수정 뷰 Service 
	 * @param noticeNo
	 * @return
	 */
	public Notice noticeModifyView(int noticeNo);

	
	/**
	 * 게시판 수정 Service
	 * @param notice
	 * @return
	 */
	public int modifyNotice(Notice notice);

	/**
	 * 게시판 삭제 Service 
	 * @param noticeNo
	 * @return
	 */
	public int deleteNotice(int noticeNo);

	

}
