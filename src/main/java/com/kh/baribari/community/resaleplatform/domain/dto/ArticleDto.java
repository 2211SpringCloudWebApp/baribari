package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.user.domain.User;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter @ToString
@NoArgsConstructor
public class ArticleDto
{
    private Integer communityNo;
    private UserDto userDto;
    private String communitySubject;
    private String communityContent;
    private int communityCategory;
    private Set<ArticleHashtagDto> articleHashtagDto;
    private String communityDate;



    public static ArticleDto of(Integer communityNo, UserDto userDto, String communitySubject,
                                String communityContent, int communityCategory, Set<ArticleHashtagDto> articleHashtagDtos) {
        return new ArticleDto(communityNo, userDto, communitySubject, communityContent, communityCategory, articleHashtagDtos, null);
    }
    public static ArticleDto of(Integer communityNo, UserDto userDto, String communitySubject,
                                String communityContent, int communityCategory, Set<ArticleHashtagDto> articleHashtagDtos,
                                String communityDate) {
        return new ArticleDto(communityNo, userDto, communitySubject, communityContent, communityCategory, articleHashtagDtos, communityDate );
    }

    public static ArticleDto of(UserDto userDto, String communitySubject,
                                String communityContent, int communityCategory, Set<ArticleHashtagDto> articleHashtagDtos) {
        return new ArticleDto(null, userDto, communitySubject, communityContent, communityCategory, articleHashtagDtos, null);
    }


    public static ArticleDto from(Article article) {
        return new ArticleDto(
                article.getCommunityNo(),
                UserDto.from(article.getUser()),
                article.getCommunitySubject(),
                article.getCommunityContent(),
                article.getCommunityCategory(),
                article.getHashtags().stream()
                        .map(ArticleHashtagDto::from)
                        .collect(Collectors.toUnmodifiableSet()),
                article.getCommunityDate()
        );
    }

    public Article toEntity(User user) {
        Article article = new Article();
        article.setCommunityNo(communityNo);
        article.setUser(user);
        article.setCommunitySubject(communitySubject);
        article.setCommunityContent(communityContent);
        article.setCommunityCategory(communityCategory); // 추가된 필드
        article.setArticleHashtags(articleHashtagDto.stream().map(ArticleHashtagDto::toEntity).collect(Collectors.toSet()));
        article.setCommunityDate(communityDate);
        return article;
    }

    public ArticleDto(Integer communityNo, UserDto userDto, String communitySubject, String communityContent, int communityCategory, Set<ArticleHashtagDto> articleHashtagDtos, String communityDate)
    {
        this.communityNo = communityNo;
        this.userDto = userDto;
        this.communitySubject = communitySubject;
        this.communityContent = communityContent;
        this.communityCategory = communityCategory;
        this.articleHashtagDto = articleHashtagDtos;
        this.communityDate = communityDate;
    }

}
