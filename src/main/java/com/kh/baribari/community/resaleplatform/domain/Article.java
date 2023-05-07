package com.kh.baribari.community.resaleplatform.domain;

import com.kh.baribari.user.domain.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.*;
@Getter @Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class Article
{
    private Integer communityNo;
    private Integer userNo;
    private User user;
    @NotBlank @Length(min=2)
    private String communitySubject;
    @NotBlank
    private String communityContent;
    private int communityCategory;
    private Set<ArticleHashtag> articleHashtags = new LinkedHashSet<>();
    private String communityDate;
    @ToString.Exclude
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();
    public Article(Integer userNo, String communitySubject, String communityContent)
    {
        this.userNo = userNo;
        this.communitySubject = communitySubject;
        this.communityContent = communityContent;
    }
    public static Article of(Integer userNo, String communitySubject, String communityContent)
    {
        return new Article(userNo, communitySubject, communityContent);
    }
    public Set<ArticleHashtag> getHashtags()
    {
        return Collections.unmodifiableSet(articleHashtags);
    }
    public void addHashtag(ArticleHashtag hashTag)
    {
        this.getArticleHashtags().add(hashTag);
    }
    public void addHashtags(Collection<ArticleHashtag> hashTag)
    {
        this.getArticleHashtags().addAll(hashTag);
    }
    public void clearHashtags()
    {
        this.getArticleHashtags().clear();
    }
    public User getUser()
    {
        return user;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(communityNo, article.communityNo);
    }
    @Override
    public int hashCode()
    {
        return Objects.hash(communityNo);
    }
}
