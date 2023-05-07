package com.kh.baribari.community.resaleplatform.service;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.ArticleComment;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleCommentDto;
import com.kh.baribari.community.resaleplatform.repository.ArticleCommentRepository;
import com.kh.baribari.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService
{

    private final ArticleCommentRepository articleCommentRepository;


    public void saveArticleComment(ArticleCommentDto dto)
    {
        try {
            Article article = articleCommentRepository.getArticleReferenceById(dto.getCommunityNo());
            User user = articleCommentRepository.getUserReferenceById(dto.getUserDto().getUserNo());
            ArticleComment articleComment = dto.toEntity(article, user);

            if (dto.getParentCommentNo() != null) {
                ArticleComment parentComment = articleCommentRepository.getReferenceById(dto.getParentCommentNo());
                parentComment.addChildComment(articleComment);
            } else {
                articleCommentRepository.save(articleComment);
            }
        } catch (Exception e) {
            log.warn("댓글 저장 실패. 댓글 작성에 필요한 정보를 찾을 수 없습니다 - {}", e.getLocalizedMessage());
            throw new RuntimeException("댓글 저장에 실패했습니다. 댓글 작성에 필요한 정보를 찾을 수 없습니다.");
        }
    }

    public void deleteArticleComment(Integer commentId, String username)
    {
        articleCommentRepository.deleteByIdAndUserAccount_UserId(commentId, username);
    }
}