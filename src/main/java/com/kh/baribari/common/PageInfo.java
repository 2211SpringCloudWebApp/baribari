/*
		1. 컨트롤러 부분에 아래 부분 추가 (매개 변수 명, 값은 수정 가능)

		PageInfo 매개변수: 현재페이지 (RequestParam), 전체 게시글 수 (mapper), 페이지 당 게시글 수
		PageInfo pi = new PageInfo(currentPage, pCount, 6);

		2. Service와 Store(Repository)로 보낼 때 pi를 매개변수로 보내줌

		3. StoreLogic(RepositoryLogic)에서 아래 부분 추가 (offset과 limit부분을 DTO에서 선언)

		RowBounds rowBounds = new RowBounds(pi.getOffset(), pi.getBoardLimit());

		4. mapper 실행 시 ("매퍼명", null, rowBounds)

		package com.kh.baribari.product.controller;
*/

package com.kh.baribari.common;

import lombok.Data;

@Data
public class PageInfo {
	private int currentPage;	// 현재 페이지
	private int boardLimit;		// 페이지 당 게시글 갯수
	private int totalCount;		// 전체 게시글 갯수
	private int navLimit;		// 페이지 당 내비게이터 갯수
	private int startNav;		// 내비게이터 시작 페이지
	private int endNav;			// 내비게이터 끝 페이지
	private int navTotalCount;	// 페이지의 마지막 번호
	private int offset;			// offset: RowBounds에서 사용
	
	public PageInfo(int currentPage, int totalCount, int boardLimit) {
		this.currentPage = currentPage;
		this.boardLimit = boardLimit;
		this.totalCount = totalCount;
		this.navLimit = 5;
		navTotalCount = (int) Math.ceil((double) totalCount / boardLimit);
		startNav = (((int)((double) currentPage / navLimit + 0.9)) - 1) * navLimit + 1;
		endNav = startNav + navLimit - 1;
		if(endNav > navTotalCount) {
			endNav = navTotalCount;
		}
		offset = (currentPage - 1) * boardLimit;
	}
}