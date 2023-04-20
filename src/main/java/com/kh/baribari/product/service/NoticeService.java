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
	

}
