package com.kh.baribari.community.communityPic.service.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.baribari.community.communityPic.repository.CommunityPicRepository;
import com.kh.baribari.community.communityPic.service.CommunityPicService;
import com.kh.baribari.community.domain.CommunityPIC;

@Service
public class CommunityPicServiceLogic implements CommunityPicService{
	@Autowired
	private CommunityPicRepository cRepository;
	
	@Autowired
	private SqlSession session;
	
	@Override
	public int getPicCount(int communityNo) {
		return cRepository.getPicCount(session, communityNo);
	}

	@Override
	public int registerPhoto(CommunityPIC pic) {
		return cRepository.registerPhoto(session, pic);
	}

	@Override
	public int updatePhoto(CommunityPIC pic) {
		return cRepository.updatePhoto(session, pic);
	}

	@Override
	public CommunityPIC showImg(int communityNo) {
		return cRepository.showImg(session, communityNo);
	}

	@Override
	public int deleteImg(CommunityPIC pic) {
		return cRepository.deleteImg(session, pic);
	}

	@Override
	public int ALLdeleteImg(int communityNo) {
		return cRepository.ALLdeleteImg(session, communityNo);
	}
}
