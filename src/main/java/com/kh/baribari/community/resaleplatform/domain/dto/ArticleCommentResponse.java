package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentResponse
{
    private Integer commentNo;
    private String userNo;
    private String userNickname;
    private Integer parentCommentNo;
    private Set<ArticleCommentResponse> childComments = new LinkedHashSet<>();
    private String commentContent;
    private String CommunityDate;

    public static ArticleCommentResponse of(Integer commentNo, String userNo,
                                            String userNickname, Integer parentCommentNo,
                                            String commentContent)
    {
        Comparator<ArticleCommentResponse> childCommentComparator = Comparator
                .comparing(ArticleCommentResponse::getCommunityDate)
                .thenComparingLong(ArticleCommentResponse::getCommentNo);
        Set<ArticleCommentResponse> childComments = new TreeSet<>(childCommentComparator);
        return new ArticleCommentResponse(commentNo, userNo, userNickname, parentCommentNo, childComments, commentContent, null);
    }

    public static ArticleCommentResponse from(ArticleCommentDto dto)
    {
        String nickname = dto.getUserDto().getUserNickName();
        if (nickname == null || nickname.isBlank())
        {
            nickname = dto.getUserDto().getUserNickName();
        }
        return new ArticleCommentResponse(
                dto.getCommentNo(),
                dto.getUserDto().getUserId(),
                dto.getUserDto().getUserNickName(),
                dto.getParentCommentNo(),
                new LinkedHashSet<>(), // Initialize an empty set for childComments
                dto.getContent(),
                dto.getCommunityDate()
        );
    }

    public boolean hasParentComment()
    {
        return parentCommentNo != null;
    }
}
