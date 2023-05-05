package com.kh.baribari.community.resaleplatform.repository.mybatis;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleDto;
import com.kh.baribari.community.resaleplatform.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository @Slf4j
@RequiredArgsConstructor
public class ArticleRepositoryImpl implements ArticleRepository
{
    private final SqlSession sqlSession;


    @Override
    public List<ArticleDto> searchArticles(Map<String, Object> params)
    {
        return sqlSession.selectList("ArticleMapper.searchArticles", params);
    }

    @Override
    public int count(Map<String, Object> params)
    {
        return sqlSession.selectOne("ArticleMapper.countArticles", params);
    }

    @Override
    public Optional<Article> findById(int communityNo)
    {
        return Optional.ofNullable(sqlSession.selectOne("ArticleMapper.findById", communityNo));
    }

    @Override
    public int countComments(int communityNo)
    {
        return sqlSession.selectOne("ArticleMapper.countComments", communityNo);
    }

    @Override
    public void save(Article article)
    {
        sqlSession.insert("ArticleMapper.saveArticle", article);
    }

    @Override
    public void update(Article article)
    {
        sqlSession.update("ArticleMapper.updateArticle", article);
    }

    @Override
    public void deleteByIdAndUserAccount_UserId(Integer communityNo, int userNo)
    {
        sqlSession.delete("ArticleMapper.deleteByIdAndUserAccount_UserId", Map.of("communityNo", communityNo, "userNo", userNo));
    }

}