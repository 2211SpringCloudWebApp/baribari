package com.kh.baribari.community.resaleplatform.repository;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ArticleRepository
{
    List<Article> findByTitleContaining(String communitySubject);
    List<Article> findByContentContaining(String communityContent);
    List<Article> findByUserAccount_UserIdContaining(String userNo);
    List<Article> findByUserAccount_NicknameContaining(String userNickname);
    List<ArticleDto> searchArticles(@Param("params") Map<String, Object> params);
    int count(Map<String, Object> params);
    void save(Article article);
    Optional<Article> findById(int communityNo);
    void update(Article article);
    void deleteByIdAndUserAccount_UserId(Integer communityNo, int userNo);
}
