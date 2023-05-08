package com.kh.baribari.community.resaleplatform.controller;

import com.kh.baribari.community.resaleplatform.domain.dto.ArticleCommentResponse;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleHashtagDto;
import com.kh.baribari.community.resaleplatform.domain.dto.GetArticleDto;
import com.kh.baribari.community.resaleplatform.domain.dto.GetArticleResponse;

import java.util.Set;
import java.util.stream.Collectors;


public class Converter
{
    public static GetArticleResponse toGetArticleResponse(GetArticleDto dto) {
        Set<ArticleCommentResponse> articleCommentsResponse = dto.getArticleCommentDto().stream()
                                                                 .map(ArticleCommentResponse::from)
                                                                 .collect(Collectors.toSet());

        GetArticleResponse response = new GetArticleResponse(
                dto.getCommunityNo(),
                dto.getCommunitySubject(),
                dto.getCommunityContent(),
                dto.getArticleHashtagDtos().stream()
                        .map(ArticleHashtagDto::getHashtagName)
                        .collect(Collectors.toSet()),
                dto.getCommunityDate(),
                (dto.getUserDto() != null) ? dto.getUserDto().getUserNickName() : "Unknown",
                (dto.getUserDto() != null) ? dto.getUserDto().getUserId() : "Unknown",
                articleCommentsResponse
        );


        response.setArticleCommentsResponse(response.organizeChildComments());
        return response;
    }


    //    public static ArticleWithCommentsDto toArticleWithCommentsDto(ArticleWithCommentResponse response) {
    //        Set<ArticleCommentDto> articleCommentDto = response.getArticleCommentsResponse().stream()
    //                .map(commentResponse -> ArticleCommentDto.builder()
    //                        .id(commentResponse.getCommentNo())
    //                        .content(commentResponse.getCommentContent())
    //                        .userDto(UserDto.builder()
    //                                .userId(commentResponse.getUserNo())
    //                                .userNickName(commentResponse.getUser().getUserNickName())
    //                                .build())
    //                        .parentCommentNo(commentResponse.getParentCommentNo() == null ? null : ArticleCommentDto.builder().id(commentResponse.getParentCommentNo())
    //                                        .build())
    //                        .build())
    //                .collect(Collectors.toSet());
    //
    //        return ArticleWithCommentsDto.builder()
    //                .communityNo(response.getCommunityNo())
    //                .userDto(UserDto.builder()
    //                        .userId(response.getUserId())
    //                        .userNickName(response.getNickname())
    //                        .build())
    //                .articleCommentDto(articleCommentDto)
    //                .communitySubject(response.getCommunitySubject())
    //                .communityContent(response.getCommunityContent())
    //                .articleHashtagDtos(response.getHashtags().stream()
    //                        .map(hashtag -> ArticleHashtagDto.builder().hashtagName(hashtag).build())
    //                        .collect(Collectors.toSet()))
    //                .communityDate(response.getCommunityDate())
    //                .build();
    //    }
}
