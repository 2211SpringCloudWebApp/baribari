package com.kh.baribari.community.resaleplatform.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kh.baribari.common.annotation.LoginUser;
import com.kh.baribari.community.resaleplatform.domain.dto.*;
import com.kh.baribari.community.resaleplatform.domain.type.ArticleAction;
import com.kh.baribari.community.resaleplatform.domain.type.SearchType;
import com.kh.baribari.community.resaleplatform.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j @Controller
@RequiredArgsConstructor
@RequestMapping("resale")
public class ArticleController
{
    private final ArticleService articleService;
    private final Converter2nd converter2nd;


    @GetMapping
    public ModelAndView getReadingList(
            @RequestParam(required = false) SearchType searchType,
            @RequestParam(required = false) String searchWord,
            @RequestParam(required = false, defaultValue = "1") int page,
            ModelAndView modelAndView
                                      )
    {
        int pageSize = 10;

        Map<String, Object> params = new HashMap<>();
        params.put("searchType", searchType != null ? searchType.toString() : null);
        params.put("searchWord", searchWord);

        PageHelper.startPage(page, pageSize);
        List<ArticleDto> articleDtos = articleService.searchArticles(params);
        PageInfo<ArticleDto> pageInfo = new PageInfo<>(articleDtos);
        PageInfo<ReadingListResponse> responsePageInfo = converter2nd.toReadingListResponse(pageInfo);

        modelAndView.addObject("articles", responsePageInfo.getList());
        modelAndView.addObject("pageInfo", responsePageInfo);
        modelAndView.addObject("SearchTypes", SearchType.values());
        modelAndView.addObject("searchTypeHashtag", SearchType.HASHTAG);
        modelAndView.setViewName("community/resale-platform/reading-list");

        return modelAndView;
    }


    @GetMapping("/article/{communityNo}")
    public String getArticle(
            @PathVariable int communityNo,
            ModelMap map
                            ) throws NotFoundException {

        GetArticleResponse article = Converter.toGetArticleResponse(articleService.getArticleComments(communityNo));
        Set<ArticleCommentResponse> organizedComments = article.organizeChildComments();
        map.addAttribute("article", article);
        map.addAttribute("articleComments", organizedComments);
        map.addAttribute("totalCount", articleService.countComments(communityNo));

        return "community/resale-platform/article";
    }


    @GetMapping("/article/new")
    public String article(ModelMap modelMap, @LoginUser LoginUserDto loginUserDto)
    {
        modelMap.addAttribute("articleAction", ArticleAction.CREATE);
        modelMap.addAttribute("loginUser", loginUserDto);
        modelMap.addAttribute("currentDate", LocalDateTime.now());
        return "community/resale-platform/article-form";
    }


    @PostMapping("/article/new")
    public String writeArticle(
            @LoginUser LoginUserDto loginUserDto,
            WriteRequest writeRequest,
            RedirectAttributes redirectAttributes
                              ) {
        if (loginUserDto != null) {
            articleService.saveArticle(writeRequest.toDto(loginUserDto.toDto()));
            return "redirect:resale";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "게시물을 생성하려면 로그인해야 합니다.");
            return "redirect:/login";
        }
    }


    @GetMapping("/article/{communityNo}/update")
    public String updateArticlePost(@PathVariable int communityNo, ModelMap map) throws NotFoundException
    {
        ReadingListResponse article = ReadingListResponse.from(articleService.getArticle(communityNo));

        map.addAttribute("article", article);
        map.addAttribute("formStatus", ArticleAction.UPDATE);

        return "community/resale-platform/article-form";
    }


    @PutMapping("/article/{communityNo}/update")
    public String updateArticlePost(
            @PathVariable int communityNo,
            @LoginUser LoginUserDto loginUserDto,
            WriteRequest writeRequest
                                   )
    {
        articleService.updateArticle(communityNo, writeRequest.toDto(loginUserDto.toDto()));

        return "redirect:/resale/articles/" + communityNo;

    }


    @DeleteMapping("/article/{communityNo}/delete")
    public String deleteArticle(
            @PathVariable Integer communityNo,
            @LoginUser LoginUserDto loginUserDto
                               )
    {
        articleService.deleteArticle(communityNo, loginUserDto.getUserNo());

        return "redirect:/resale";
    }

}






