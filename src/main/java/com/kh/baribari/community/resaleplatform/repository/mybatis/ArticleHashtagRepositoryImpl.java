package com.kh.baribari.community.resaleplatform.repository.mybatis;
import com.kh.baribari.community.resaleplatform.domain.ArticleHashtag;
import com.kh.baribari.community.resaleplatform.repository.ArticleHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
@RequiredArgsConstructor
public class ArticleHashtagRepositoryImpl implements ArticleHashtagRepository
{
    private final SqlSession sqlSession;
    @Override
    public Optional<ArticleHashtag> findByHashtagName(String hashtagName)
    {
        return Optional.ofNullable(sqlSession.selectOne("ArticleHashtagMapper.findByHashtagName", hashtagName));
    }
    @Override
    public List<ArticleHashtag> findByHashtagNameIn(Set<String> hashtagNames)
    {
        return sqlSession.selectList("ArticleHashtagMapper.findByHashtagNameIn", hashtagNames);
    }
    @Override
    public Optional<ArticleHashtag> findById(Integer communityNo)
    {
        return Optional.ofNullable(sqlSession.selectOne("ArticleHashtagMapper.findById", communityNo));
    }
    @Override
    public void save(ArticleHashtag hashtag) {
        if (hashtag.getCommunityNo() == null) {
            sqlSession.insert("ArticleHashtagMapper.insertHashtag", hashtag);
        } else {
            sqlSession.update("ArticleHashtagMapper.updateHashtag", hashtag);
        }
    }
    @Override
    public void delete(ArticleHashtag articleHashtag)
    {
        sqlSession.delete("ArticleHashtagMapper.delete", articleHashtag);
    }
    @Override
    public List<String> findAllHashtagNames() {
        return sqlSession.selectList("ArticleHashtagMapper.findAllHashtagNames");
    }
}