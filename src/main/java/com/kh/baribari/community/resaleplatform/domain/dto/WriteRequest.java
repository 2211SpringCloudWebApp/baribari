package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class WriteRequest
{
    private String communitySubject;
    private String communityContent;

    public static WriteRequest of(String communitySubject, String communityContent)
    {
        return new WriteRequest(communitySubject, communityContent);
    }

    public ArticleDto toDto(UserDto userAccountDto)
    {
        return toDto(userAccountDto, null);
    }

    public ArticleDto toDto(UserDto userAccountDto, Set<ArticleHashtagDto> articleHashtagDto)
    {
        return ArticleDto.of(
                userAccountDto,
                communitySubject,
                communityContent,
                articleHashtagDto
                            );
    }
}
