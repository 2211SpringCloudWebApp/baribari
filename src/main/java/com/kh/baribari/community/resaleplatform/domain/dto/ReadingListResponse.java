package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

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

    public static ReadingListResponse from(ArticleDto articleDto) {
        ReadingListResponse response = new ReadingListResponse();
        response.setCommunityNo(articleDto.getCommunityNo());
        response.setCommunitySubject(articleDto.getCommunitySubject());
        response.setCommunityContent(articleDto.getCommunityContent());
        response.setCommunityDate(articleDto.getCommunityDate());
        UserDto userDto = articleDto.getUserDto();
        if (userDto != null) {
            response.setUserNickName(userDto.getUserNickName());
        } else {
            response.setUserNickName("Unknown User");
        }
        return response;
    }

    LocalDateTime date = LocalDateTime.parse("20230507101110", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public static ReadingListResponse fromArticleDto(ArticleDto articleDto) {
        return from(articleDto);
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }
}