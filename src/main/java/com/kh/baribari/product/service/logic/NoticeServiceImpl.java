package com.kh.baribari.product.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Notice;
import com.kh.baribari.product.repository.NoticeRepository;
import com.kh.baribari.product.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	private NoticeRepository nRepository;
	@Autowired
	private SqlSession session;

	@Override
	public List<Notice> selectNoticeList(PageInfo pi) {
		List<Notice> nList = nRepository.selectNoticeList(session, pi);
		return nList;
	}

	@Override
	public int noticeListCount() {
		int result = nRepository.noticeListCount(session);
		return result;
	}

	@Override
	public int updateViewCount(int noticeNo) {
		int result = nRepository.updateViewCount(session, noticeNo);
		return result;
	}

	@Override
	public Notice selectOneByNo(int noticeNo) {
		Notice notice = nRepository.selectOneByNo(session, noticeNo);
		return notice;
	}

}
