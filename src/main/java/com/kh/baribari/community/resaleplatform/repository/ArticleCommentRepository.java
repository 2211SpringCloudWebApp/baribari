package com.kh.baribari.community.resaleplatform.repository;


import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.ArticleComment;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleCommentDto;
import com.kh.baribari.user.domain.User;

import java.util.List;

public interface ArticleCommentRepository {

    List<ArticleCommentDto> findCommentsByCommunityNo(int communityNo);

    void deleteByIdAndUserNo(int commentNo, int userNo);



    List<ArticleComment> findByArticle_Id(Integer articleId);
    void save(ArticleComment articleComment);
    void deleteByIdAndUserAccount_UserId(Integer articleCommentId, String userId);
    Article getArticleReferenceById(Integer articleId);
    User getUserReferenceById(Integer userId);
    ArticleComment getReferenceById(Integer parentCommentId);
}