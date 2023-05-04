package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.ArticleComment;
import com.kh.baribari.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCommentDto
{
    private Integer commentNo;
    private Integer communityNo;
    private UserDto userDto;
    private String communityDate;
    private Integer parentCommentNo;
    private String content;

    public static ArticleCommentDto of(Integer communityNo, UserDto userDto, String content)
    {
        return new ArticleCommentDto(null, communityNo, userDto, null, null, content);
    }

    public static ArticleCommentDto of(Integer communityNo, UserDto userDto,
                                       Integer parentCommentNo, String content)
    {
        return new ArticleCommentDto(null, communityNo, userDto, null, parentCommentNo, content);
    }

    public static ArticleCommentDto from(ArticleComment entity)
    {
        return new ArticleCommentDto(
                entity.getCommentNo(),
                entity.getArticle().getCommunityNo(),
                UserDto.from(entity.getUser()),
                null, // communityDate
                entity.getParentCommentNo(),
                entity.getCommentContent()
        );
    }

    public ArticleComment toEntity(Article article, User user)
    {
        ArticleComment articleComment = new ArticleComment();
        articleComment.setArticle(article);
        articleComment.setUser(user);
        articleComment.setParentCommentNo(parentCommentNo);
        articleComment.setCommentContent(content);
        return articleComment;
    }
}