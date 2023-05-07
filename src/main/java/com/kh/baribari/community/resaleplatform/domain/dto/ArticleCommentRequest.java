package com.kh.baribari.community.resaleplatform.domain.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleCommentRequest
{

    private Integer communityNo;
    private Integer parentCommentNo;
    private String commentContent;



    public static ArticleCommentRequest of(Integer communityNo, String commentContent)
    {
        return new ArticleCommentRequest(communityNo, null, commentContent);
    }

    public static ArticleCommentRequest of(Integer communityNo, Integer parentCommentNo, String commentContent)
    {
        return new ArticleCommentRequest(communityNo, parentCommentNo, commentContent);
    }

    public ArticleCommentDto toDto(UserDto userDto)
    {
        return ArticleCommentDto.of(
                communityNo,
                userDto,
                parentCommentNo,
                commentContent
                                   );
    }
   


    public Integer getArticleNo() {
        return communityNo;
    }

    public void setArticleNo(Integer communityNo) {
        this.communityNo = communityNo;
    }

    public Integer getParentCommentNo() {
        return parentCommentNo;
    }

    public void setParentCommentNo(Integer parentCommentNo) {
        this.parentCommentNo = parentCommentNo;
    }

    public String getContent() {
        return commentContent;
    }

    public void setContent(String commentContent) {
        this.commentContent = commentContent;
    }
}