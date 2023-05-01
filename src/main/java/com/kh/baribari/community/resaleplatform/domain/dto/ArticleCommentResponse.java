package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.Article;
import lombok.*;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class ArticleCommentResponse
{

    private Integer commentNo;
    private Article article;
    private String userNo;
    private String userNickname;
    private Integer parentCommentNo;
    private Set<ArticleCommentResponse> childComments = new LinkedHashSet<>();
    private String commentContent;
    private String CommunityDate;

    public static ArticleCommentResponse of(Integer commentNo, Article article, String userNo, String userNickname,
                                            Integer parentCommentNo, String commentContent)
    {
        return ArticleCommentResponse.of(commentNo, article, userNo, userNickname, parentCommentNo, new LinkedHashSet<>(), commentContent);
    }

    public static ArticleCommentResponse of(Integer commentNo, Article article, String userNo, String userNickname, Integer parentCommentNo,
                                            Set<ArticleCommentResponse> childComments, String commentContent)
    {
        Comparator<ArticleCommentResponse> childCommentComparator = Comparator
                .comparing(ArticleCommentResponse::getCommunityDate)
                .thenComparingLong(ArticleCommentResponse::getCommentNo);

        return ArticleCommentResponse.builder()
                .commentNo(commentNo)
                .article(article)
                .userNo(userNo)
                .userNickname(userNickname)
                .parentCommentNo(parentCommentNo)
                .childComments(new TreeSet<>(childCommentComparator))
                .commentContent(commentContent)
                .build();
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto)
    {
        String nickname = dto.getUserDto().getUserNickName();
        if (nickname == null || nickname.isBlank())
        {
            nickname = dto.getUserDto().getUserNickName();
        }

        return ArticleCommentResponse.builder()
                .commentNo(dto.getCommentNo())
                .commentContent(dto.getContent())
                .CommunityDate(dto.getCommunityDate())
                .parentCommentNo(dto.getParentCommentNo())
                .userNo(dto.getUserDto().getUserId())
                .userNickname(dto.getUserDto().getUserNickName())
                .build();

    }

    private Integer communityNo;

    private UserDto userDto;

    public boolean hasParentComment()
    {
        return parentCommentNo != null;
    }
}