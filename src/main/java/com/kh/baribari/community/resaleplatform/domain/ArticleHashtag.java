package com.kh.baribari.community.resaleplatform.domain;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor (access = AccessLevel.PROTECTED)
public class ArticleHashtag
{ private Integer communityNo;
    private String hashtagName;
    private Set<Article> articles = new LinkedHashSet<>();
    private ArticleHashtag(String hashtagName)
    {
        this.hashtagName = hashtagName;
    }
    public static ArticleHashtag of(String hashtagName)
    {
        return new ArticleHashtag(hashtagName);
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ArticleHashtag)) return false;
        ArticleHashtag hashtag = (ArticleHashtag) o;
        return Objects.equals(communityNo, hashtag.communityNo);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(communityNo);
    }
}