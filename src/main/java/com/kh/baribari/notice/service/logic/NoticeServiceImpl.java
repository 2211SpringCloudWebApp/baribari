package com.kh.baribari.notice.service.logic;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.notice.domain.Notice;
import com.kh.baribari.notice.repository.NoticeRepository;
import com.kh.baribari.notice.service.NoticeService;

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

	@Override
	public int noticeSearch(Search search) {
		int totalCount = nRepository.noticeSearch(session, search);
		return totalCount;
	}

	@Override
	public List<Notice> selectListByKeyword(PageInfo pi, Search search) {
		List<Notice> searchList = nRepository.selectListByKeyword(session, pi, search);
		return searchList;
	}

	@Override
	public int writeNotice(Notice notice) {
		int result = nRepository.writeNotice(session, notice);
		return result;
	}

	@Override
	public Notice noticeModifyView(int noticeNo) {
		Notice notice = nRepository.noticeModifyView(session, noticeNo);
		return notice;
	}

	@Override
	public int modifyNotice(Notice notice) {
		int result = nRepository.modifyNotice(session, notice);
		return result;
	}

	@Override
	public int deleteNotice(int noticeNo) {
		int result = nRepository.deleteNotice(session, noticeNo);
		return result;
	}

}
