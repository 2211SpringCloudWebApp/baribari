package com.kh.baribari.community.communityPic.repository;

import org.apache.ibatis.session.SqlSession;

import com.kh.baribari.community.domain.CommunityPIC;

public interface CommunityPicRepository {

	/**
	 * 등록되어 있는 사진 수 출력
	 * @param session
	 * @param communityNo
	 * @return
	 */
	public int getPicCount(SqlSession session, int communityNo);

	/**
	 * 사진등록
	 * @param session
	 * @param pic
	 * @return
	 */
	public int registerPhoto(SqlSession session, CommunityPIC pic);

	/**
	 * 사진 수정, 추가
	 * @param session
	 * @param pic
	 * @return
	 */
	public int updatePhoto(SqlSession session, CommunityPIC pic);

	/**
	 * 사진 보여주기
	 * @param session
	 * @param communityNo
	 * @return
	 */
	public CommunityPIC showImg(SqlSession session, int communityNo);

	/**
	 * 이미지 삭제
	 * @param session
	 * @param pic
	 * @return
	 */
	public int deleteImg(SqlSession session, CommunityPIC pic);
	
	/**
	 * 전체 이미지 삭제
	 * @param session
	 * @param communityNo
	 */
	public int ALLdeleteImg(SqlSession session, int communityNo);
}
