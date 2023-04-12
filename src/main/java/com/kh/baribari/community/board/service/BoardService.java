package com.kh.baribari.community.board.service;

import java.util.List;

import com.kh.baribari.common.PageInfo;
import com.kh.baribari.community.domain.Community;

public interface BoardService {

	public int getBoardCount();

	public List<Community> getBoardListAll(PageInfo pi);

}
