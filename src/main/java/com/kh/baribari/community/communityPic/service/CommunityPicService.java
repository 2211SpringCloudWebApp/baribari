package com.kh.baribari.community.communityPic.service;

import com.kh.baribari.community.domain.CommunityPIC;

public interface CommunityPicService {

	/**
	 * 등록되어 있는 사진 수 출력
	 * @param communityNo
	 * @return
	 */
	public int getPicCount(int communityNo);
	
	/**
	 * 사진등록
	 * @param pic
	 * @return int
	 */
	public int registerPhoto(CommunityPIC pic);

	/**
	 * 사진 수정, 추가
	 * @param pic
	 * @return
	 */
	public int updatePhoto(CommunityPIC pic);

	/**
	 * 사진 보여주기
	 * @param communityNo
	 * @return
	 */
	public CommunityPIC showImg(int communityNo);

	/**
	 * 이미지 삭제
	 * @param pic
	 * @return
	 */
	public int deleteImg(CommunityPIC pic);

	/**
	 * 이미지 전체 삭제
	 * @param communityNo
	 */
	public int ALLdeleteImg(int communityNo);
}
