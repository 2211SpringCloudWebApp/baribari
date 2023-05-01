package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.ArticleComment;
import com.kh.baribari.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
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
        return ArticleCommentDto.builder()
                .communityNo(communityNo)
                .userDto(userDto)
                .content(content)
                .build();
    }

    public static ArticleCommentDto of(Integer communityNo, UserDto userDto,
                                       Integer parentCommentNo, String content)
    {
        return ArticleCommentDto.builder()
                .communityNo(communityNo)
                .userDto(userDto)
                .parentCommentNo(parentCommentNo)
                .content(content)
                .build();
    }

    public static ArticleCommentDto from(ArticleComment entity)
    {
        return ArticleCommentDto.builder()
                .commentNo(entity.getCommentNo())
                .communityNo(entity.getArticle().getCommunityNo())
                .userDto(UserDto.from(entity.getUser()))
                .parentCommentNo(entity.getParentCommentNo())
                .content(entity.getCommentContent())
                .build();
    }

    public ArticleComment toEntity(Article article, User user)
    {
        return ArticleComment.builder()
                .article(article)
                .user(user)
                .parentCommentNo(parentCommentNo)
                .commentContent(content)
                .build();
    }
}