package com.kh.baribari.common.scrap.domain;

import lombok.Data;

@Data
public class Scrap {
	private int communityNo; // 커뮤니티에서는 시퀀스로 사용, 상품찜하기에서는 99999+productNo
	private int userNo;
}
