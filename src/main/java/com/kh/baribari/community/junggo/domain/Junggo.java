package com.kh.baribari.community.junggo.domain;

import com.kh.baribari.user.user.domain.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @ToString
@AllArgsConstructor @NoArgsConstructor
public class Junggo {

    private Long communityNo; // 중고글 번호

    @Setter @NotBlank @Length(min=2)
    private String communitySubject; // 중고글 제목
    @Setter @NotBlank
    private String communityComment; // 중고글 내용
    @Setter
    private String communityHashTag; // 해시 태그

    private User userNo; // 작성자
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime communityDate; // 작성 일시


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Junggo junggo = (Junggo) o;
        return communityNo.equals(junggo.communityNo);
    }
    @Override
    public int hashCode() {
        return Objects.hash(communityNo);
    }
}
