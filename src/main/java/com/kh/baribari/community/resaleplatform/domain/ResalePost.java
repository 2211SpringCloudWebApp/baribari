package com.kh.baribari.community.resaleplatform.domain;

import com.kh.baribari.user.domain.User;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter @ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResalePost
{

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

/*
    TODO : '가격' 추가 고민하기
     방법 1. 리스트 배열로 제목에 가격도 때려 넣기
     방법 2. DB 생성   private Integer Price;
     방법 3. 상품 앤티티 참고

*/

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResalePost resalePost = (ResalePost) o;
        return communityNo.equals(resalePost.communityNo);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(communityNo);
    }
}
