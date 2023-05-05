package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
import java.util.stream.Collectors;
@Getter @ToString
@NoArgsConstructor @AllArgsConstructor
public class GetArticleDto
{

 private Integer communityNo;
 private UserDto userDto;
 private Set<ArticleCommentDto> articleCommentDto;
 private String communitySubject;
 private String communityContent;
 private Set<ArticleHashtagDto> articleHashtagDtos;
 private String communityDate;

 public static GetArticleDto of(Integer communityNo, UserDto userDto,
                                Set<ArticleCommentDto> articleCommentDto,
                                String communitySubject, String communityContent,
                                Set<ArticleHashtagDto> articleHashtagDtos,
                                String communityDate)
 {
  return new GetArticleDto(communityNo, userDto, articleCommentDto, communitySubject, communityContent, articleHashtagDtos, communityDate);
 }

 public static GetArticleDto from(Article article)
 {
  return new GetArticleDto(
          article.getCommunityNo(),
          UserDto.from(article.getUser()),
          article.getArticleComments().stream()
                 .map(ArticleCommentDto::from)
                 .collect(Collectors.toSet()),
          article.getCommunitySubject(),
          article.getCommunityContent(),
          article.getHashtags().stream()
                 .map(ArticleHashtagDto::from)
                 .collect(Collectors.toSet()),
          article.getCommunityDate()
  );
 }
}