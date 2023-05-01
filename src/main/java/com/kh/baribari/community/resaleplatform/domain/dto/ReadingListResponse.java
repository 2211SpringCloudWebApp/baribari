package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.*;
import java.util.Set;
import java.util.stream.Collectors;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadingListResponse
{
    private int communityNo;
    private String communitySubject;
    private String communityContent;
    private Set<String> hashtags;
    private String userNickName;
    private String communityDate;

    public static ReadingListResponse of(int communityNo, String communitySubject,
                                         String communityContent, Set<String> hashtags,
                                         String userNo, String communityDate )
    {
        ReadingListResponse responseDto = new ReadingListResponse();
        responseDto.setCommunityNo(communityNo);
        responseDto.setCommunitySubject(communitySubject);
        responseDto.setCommunityContent(communityContent);
        responseDto.setHashtags(hashtags);
        responseDto.setCommunityDate(communityDate);
        responseDto.setUserNickName(userNo);
        return responseDto;
    }

    public static ReadingListResponse from(ArticleDto dto)
    {
        String userNickName = dto.getUserDto().getUserNickName();
        if (userNickName == null || userNickName.isBlank())
        {
            userNickName = dto.getUserDto().getUserNickName();
        }

        Set<String> hashtags = dto.getArticleHashtagDto().stream()
                .map(ArticleHashtagDto::getHashtagName)
                .collect(Collectors.toUnmodifiableSet());

        return ReadingListResponse.of(
                dto.getCommunityNo(),
                dto.getCommunitySubject(),
                dto.getCommunityContent(),
                hashtags,
                dto.getUserDto().getUserNickName(),
                dto.getCommunityDate()

        );
    }

    public static ReadingListResponse fromArticleDto(ArticleDto articleDto) {
        ReadingListResponse response = new ReadingListResponse();
        return response;
    }
}