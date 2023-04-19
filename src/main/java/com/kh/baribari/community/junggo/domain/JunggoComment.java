package com.kh.baribari.community.junggo.domain;

import com.kh.baribari.user.domain.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class JunggoComment {

    private Long commentNo; //댓글 번호

    private Junggo communityNo; //게시글 번호
    @Setter @NotBlank @Length(min = 2)
    private String commentContent; // 댓글 내용

    private User userNo; // 작성자


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JunggoComment that = (JunggoComment) o;
        return commentNo.equals(that.commentNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentNo);
    }
}