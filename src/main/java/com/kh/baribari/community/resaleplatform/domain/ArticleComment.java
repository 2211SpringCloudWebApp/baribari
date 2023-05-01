package com.kh.baribari.community.resaleplatform.domain;

import com.kh.baribari.user.domain.User;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor @Builder
public class ArticleComment {

    private int commentNo;
    private Article article;
    private int userNo;
    private User user;
    private int parentCommentNo;
    private Set<ArticleComment> childComments = new LinkedHashSet<>();
    private String commentContent;

    public static ArticleComment of(Article article, int userNo, String commentContent) {
        return ArticleComment.builder()
                .article(article)
                .userNo(userNo)
                .commentContent(commentContent)
                .build();
    }

    public void addChildComment(ArticleComment child) {
        child.setParentCommentNo(this.getCommentNo());
        this.getChildComments().add(child);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof ArticleComment)) return false;
        ArticleComment that = (ArticleComment) o;
        return Objects.equals(commentNo, that.commentNo);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(commentNo);
    }
}
