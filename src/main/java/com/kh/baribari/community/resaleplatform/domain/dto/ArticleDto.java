package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.user.domain.User;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDto
{
    private Integer communityNo;
    private UserDto userDto;
    private String communitySubject;
    private String communityContent;
    private Set<ArticleHashtagDto> articleHashtagDto;
    private String communityDate;


    public static ArticleDto of(UserDto userDto, String communitySubject,
                                String communityContent, Set<ArticleHashtagDto> articleHashtagDtos)
    {
        return new ArticleDto(null, userDto, communitySubject, communityContent, articleHashtagDtos, null);
    }

    public static ArticleDto of(Integer communityNo, UserDto userDto, String communitySubject,
                                String communityContent, Set<ArticleHashtagDto> articleHashtagDtos,
                                String communityDate)
    {
        return new ArticleDto(communityNo, userDto, communitySubject, communityContent, articleHashtagDtos,  communityDate);
    }

    public static ArticleDto from(Article article)
    {
        return new ArticleDto(
                article.getCommunityNo(),
                UserDto.from(article.getUser()),
                article.getCommunitySubject(),
                article.getCommunityContent(),
                article.getHashtags().stream()
                        .map(ArticleHashtagDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
                article.getCommunityDate()
        );
    }

    public Article toEntity(User user)
    {
        return Article.builder()
                .communityNo(communityNo)
                .userNo((long) user.getUserNo())
                .communitySubject(communitySubject)
                .communityContent(communityContent)
                .articleHashtags(articleHashtagDto.stream().map(ArticleHashtagDto::toEntity).collect(Collectors.toSet()))
                .communityDate(communityDate)
                .build();
    }
}
