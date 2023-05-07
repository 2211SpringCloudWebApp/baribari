package com.kh.baribari.community.communityPic.repository.logic;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.kh.baribari.community.communityPic.repository.CommunityPicRepository;
import com.kh.baribari.community.domain.CommunityPIC;

@Repository
public class CommunityPicRepositoryLogic implements CommunityPicRepository{

	@Override
	public int getPicCount(SqlSession session, int communityNo) {
		return session.selectOne("CommunityPicMapper.getPicCount", communityNo);
	}

	@Override
	public int registerPhoto(SqlSession session, CommunityPIC pic) {
		return session.insert("CommunityPicMapper.registerPhoto",pic);
	}

	@Override
	public int updatePhoto(SqlSession session, CommunityPIC pic) {
		return session.update("CommunityPicMapper.updatePhoto", pic);
	}

	@Override
	public CommunityPIC showImg(SqlSession session, int communityNo) {
		return session.selectOne("CommunityPicMapper.selectOne",communityNo);
	}

	@Override
	public int deleteImg(SqlSession session, CommunityPIC pic) {
		return session.update("CommunityPicMapper.deleteImg",pic);
	}

	@Override
	public int ALLdeleteImg(SqlSession session, int communityNo) {
		return session.delete("CommunityPicMapper.ALLdeleteImg",communityNo);
	}
}
