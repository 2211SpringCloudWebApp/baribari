package com.kh.baribari.community.resaleplatform.repository.mybatis;


import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.ArticleComment;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleCommentDto;
import com.kh.baribari.community.resaleplatform.repository.ArticleCommentRepository;
import com.kh.baribari.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ArticleCommentRepositoryImpl implements ArticleCommentRepository
{
    private final SqlSession sqlSession;


    @Override
    public List<ArticleCommentDto> findCommentsByCommunityNo(int communityNo) {
        return sqlSession.selectList("ArticleCommentMapper.findCommentsByCommunityNo", communityNo);
    }

    @Override
    public void deleteByIdAndUserNo(int commentNo, int userNo) {
        sqlSession.delete("ArticleCommentMapper.deleteByIdAndUserNo", Map.of("commentNo", commentNo, "userNo", userNo));
    }



      @Override
      public List<ArticleComment> findByArticle_Id(Integer articleId) {
         return sqlSession.selectList("ArticleCommentMapper.findByArticleId", articleId);
      }

      @Override
      public void save(ArticleComment articleComment) {
         sqlSession.insert("ArticleCommentMapper.insertArticleComment", articleComment);
      }

      @Override
      public void deleteByIdAndUserAccount_UserId(Integer articleCommentId, String userId) {
         sqlSession.delete("ArticleCommentMapper.deleteByIdAndUserId", Map.of("id", articleCommentId, "userId", userId));
      }

      @Override
      public Article getArticleReferenceById(Integer articleId) {
         return sqlSession.selectOne("ArticleMapper.selectArticleById", articleId);
      }

      @Override
      public User getUserReferenceById(Integer userId) {
         return sqlSession.selectOne("UserMapper.selectUserById", userId);
      }

      @Override
      public ArticleComment getReferenceById(Integer parentCommentId) {
         return sqlSession.selectOne("ArticleCommentMapper.selectCommentById", parentCommentId);
      }
   }