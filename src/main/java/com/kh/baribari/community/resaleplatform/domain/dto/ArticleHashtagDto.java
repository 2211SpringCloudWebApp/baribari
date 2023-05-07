package com.kh.baribari.community.resaleplatform.domain.dto;

import com.kh.baribari.community.resaleplatform.domain.ArticleHashtag;
import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
public class ArticleHashtagDto
{
    private Integer communityNo;
    private String hashtagName;
    public static ArticleHashtagDto from(ArticleHashtag articleHashtag) {
        return new ArticleHashtagDto(articleHashtag.getCommunityNo(), articleHashtag.getHashtagName());
    }
    public static ArticleHashtagDto of(String hashtagName) {
        return new ArticleHashtagDto(null, hashtagName);
    }
    public static ArticleHashtagDto of(Integer communityNo, String hashtagName) {
        return new ArticleHashtagDto(communityNo, hashtagName);
    }
    public ArticleHashtag toEntity() {
        return ArticleHashtag.of(hashtagName);
    }
}
