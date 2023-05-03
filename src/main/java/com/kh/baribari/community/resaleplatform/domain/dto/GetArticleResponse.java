package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.*;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class GetArticleResponse
{
    private Integer communityNo;
    private String communitySubject;
    private String communityContent;
    private Set<String> hashtags;
    //    private LocalDateTime communityDate;
    private String communityDate;
    private String nickname;
    private String userId;
    private Set<ArticleCommentResponse> articleCommentsResponse;

    public static GetArticleResponse of(Integer communityNo, String communitySubject, String communityContent,
                                        Set<String> hashtags, String communityDate, String nickname, String userId,
                                        Set<ArticleCommentResponse> articleCommentsResponse)
    {
        return GetArticleResponse.builder()
                .communityNo(communityNo)
                .communitySubject(communitySubject)
                .communityContent(communityContent)
                .hashtags(hashtags)
                .communityDate(communityDate)
                .nickname(nickname)
                .userId(userId)
                .articleCommentsResponse(articleCommentsResponse)
                .build();
    }

    public static GetArticleResponse from(GetArticleResponse dto)
    {
        return GetArticleResponse.builder()
                .communityNo(dto.getCommunityNo())
                .communitySubject(dto.getCommunitySubject())
                .communityContent(dto.getCommunityContent())
                .hashtags(dto.getHashtags())
                .communityDate(dto.getCommunityDate())
                .nickname(dto.getNickname())
                .userId(dto.getUserId())
                .articleCommentsResponse(dto.getArticleCommentsResponse())
                .build();
    }



    public Set<ArticleCommentResponse> organizeChildComments()
    {
        TreeSet<ArticleCommentResponse> sortedComments = articleCommentsResponse.stream()
                .sorted(Comparator
                        .comparing(ArticleCommentResponse::getCommunityDate)
                        .reversed()
                        .thenComparingLong(ArticleCommentResponse::getCommentNo)
                )
                .collect(Collectors.toCollection(TreeSet::new));

        for (ArticleCommentResponse comment : sortedComments)
        {
            if (comment.getParentCommentNo() != null)
            {
                sortedComments.stream()
                        .filter(c -> c.getCommentNo().equals(comment.getParentCommentNo()))
                        .findFirst().ifPresent(parentComment -> parentComment.getChildComments().add(comment));
            }
        }

        return sortedComments.stream()
                .filter(comment -> comment.getParentCommentNo() == null)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}