package com.kh.baribari.product.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
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
	

}
