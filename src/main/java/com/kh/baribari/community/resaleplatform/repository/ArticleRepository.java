package com.kh.baribari.community.resaleplatform.repository;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleRepository
{

    int count(Map<String, Object> params);
    List<ArticleDto> searchArticles(Map<String, Object> params);

    Optional<Article> findById(int communityNo);

    int countComments(int communityNo);

    void save(Article article);

    void update(Article article);

    void deleteByIdAndUserAccount_UserId(Integer communityNo, int userNo);

}
