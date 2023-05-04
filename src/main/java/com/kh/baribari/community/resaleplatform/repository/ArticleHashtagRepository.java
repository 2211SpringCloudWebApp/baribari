package com.kh.baribari.community.resaleplatform.repository;

import com.kh.baribari.community.resaleplatform.domain.ArticleHashtag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleHashtagRepository
{
    Optional<ArticleHashtag> findByHashtagName(String hashtagName);

    List<ArticleHashtag> findByHashtagNameIn(Set<String> hashtagNames);

    Optional<ArticleHashtag> findById(Integer hashtagId);

    void delete(Integer communityNo);
}
