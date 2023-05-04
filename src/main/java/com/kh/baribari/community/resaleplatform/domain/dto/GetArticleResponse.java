package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public static GetArticleResponse create(Integer communityNo, String communitySubject, String communityContent,
                                            Set<String> hashtags, String communityDate, String nickname, String userId,
                                            Set<ArticleCommentResponse> articleCommentsResponse)
    {
        return new GetArticleResponse(communityNo, communitySubject, communityContent, hashtags, communityDate, nickname, userId, articleCommentsResponse);
    }

    public static GetArticleResponse from(GetArticleResponse dto)
    {
        return create(dto.getCommunityNo(), dto.getCommunitySubject(), dto.getCommunityContent(),
                dto.getHashtags(), dto.getCommunityDate(), dto.getNickname(), dto.getUserId(),
                dto.getArticleCommentsResponse());
    }

    public Set<ArticleCommentResponse> organizeChildComments()
    {
        return articleCommentsResponse.stream()
                                      .filter(comment -> comment.getParentCommentNo() == null)
                                      .peek(parentComment -> parentComment.getChildComments().addAll(
                                              articleCommentsResponse.stream()
                                                                     .filter(comment -> comment.getParentCommentNo() != null && comment.getParentCommentNo().equals(parentComment.getCommentNo()))
                                                                     .collect(Collectors.toList())
                                                                                                    ))
                                      .sorted(Comparator
                                              .comparing(ArticleCommentResponse::getCommunityDate)
                                              .reversed()
                                              .thenComparingLong(ArticleCommentResponse::getCommentNo)
                                             )
                                      .collect(Collectors.toCollection(TreeSet::new));
    }
}