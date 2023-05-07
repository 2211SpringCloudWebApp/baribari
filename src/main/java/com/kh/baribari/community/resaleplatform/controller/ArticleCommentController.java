package com.kh.baribari.community.resaleplatform.controller;

import com.kh.baribari.community.resaleplatform.domain.dto.ArticleCommentRequest;
import com.kh.baribari.community.resaleplatform.domain.dto.LoginUserDto;
import com.kh.baribari.community.resaleplatform.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class ArticleCommentController
{

    private final ArticleCommentService articleCommentService;


    @PostMapping("/new")
    public String postNewArticleComment(
//            @LoginUser LoginUserDto loginUserDto,
            LoginUserDto loginUserDto,
            ArticleCommentRequest articleCommentRequest
    ) {
        articleCommentService.saveArticleComment(articleCommentRequest.toDto(loginUserDto.toDto()));

        return "redirect:/articles/" + articleCommentRequest.getArticleNo();
    }

    @PostMapping("/{commentNo}/delete")
    public String deleteArticleComment(
            @PathVariable Integer commentNo,
//            @LoginUser LoginUserDto loginUserDto,
            LoginUserDto loginUserDto,
            Integer communityNo
    ) {
        articleCommentService.deleteArticleComment(commentNo, loginUserDto.getUsername());

        return "redirect:/articles/" + communityNo;
    }

}