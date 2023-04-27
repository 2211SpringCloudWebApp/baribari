package com.kh.baribari.notice.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.common.Search;
import com.kh.baribari.notice.domain.Notice;
import com.kh.baribari.notice.repository.NoticeRepository;

@Repository
public class NoticeRepositoryLogic implements NoticeRepository{

	@Override
	public List<Notice> selectNoticeList(SqlSession session, PageInfo pi) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return nList;
	}

	@Override
	public int noticeListCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.noticeListCount");
		return result;
	}

	@Override
	public int updateViewCount(SqlSession session, int noticeNo) {
		int result = session.update("NoticeMapper.updateViewCount", noticeNo);
		return result;
	}

	@Override
	public Notice selectOneByNo(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.selectOneByNo", noticeNo);
		return notice;
	}

	@Override
	public int noticeSearch(SqlSession session, Search search) {
		int result = session.selectOne("NoticeMapper.noticeSearch" ,search);
		return result;
	}

	@Override
	public List<Notice> selectListByKeyword(SqlSession session, PageInfo pi, Search search) {
		int limit = pi.getBoardLimit();
		int currentPage = pi.getCurrentPage();
		int offset = (currentPage - 1)*limit;
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<Notice> searchList = session.selectList("NoticeMapper.selectListByKeyword", search, rowBounds);
		return searchList;
	}

	@Override
	public int writeNotice(SqlSession session, Notice notice) {
		int result = session.insert("NoticeMapper.writeNotice", notice);
		return result;
	}

	@Override
	public Notice noticeModifyView(SqlSession session, int noticeNo) {
		Notice notice = session.selectOne("NoticeMapper.noticeModifyView", noticeNo);
		return notice;
	}

	@Override
	public int modifyNotice(SqlSession session, Notice notice) {
		int result = session.update("NoticeMapper.modifyNotice", notice);
		return result;
	}

	@Override
	public int deleteNotice(SqlSession session, int noticeNo) {
		int result = session.delete("NoticeMapper.deleteNotice", noticeNo);
		return result;
	}
}
