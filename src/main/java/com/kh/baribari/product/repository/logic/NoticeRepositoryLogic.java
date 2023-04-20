package com.kh.baribari.product.repository.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.product.domain.Notice;
import com.kh.baribari.product.repository.NoticeRepository;

@Repository
public class NoticeRepositoryLogic implements NoticeRepository{

	@Override
	public List<Notice> selectNoticeList(SqlSession session, PageInfo pi) {
		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());
		List<Notice> nList = session.selectList("NoticeMapper.selectNoticeList", null, rowBounds);
		return null;
	}

	@Override
	public int noticeListCount(SqlSession session) {
		int result = session.selectOne("NoticeMapper.noticeListCount");
		return result;
	}

}
