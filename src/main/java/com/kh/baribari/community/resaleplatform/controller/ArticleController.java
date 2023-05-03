package com.kh.baribari.community.resaleplatform.controller;


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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j @Controller
@RequiredArgsConstructor
@RequestMapping("resale")
public class ArticleController
{
    private final ArticleService articleService;

    @GetMapping
    public ModelAndView getReadingList(
            @RequestParam(required = false, defaultValue = "BOTH") SearchType searchType,
            @RequestParam(required = false) String searchWord,
            @RequestParam(required = false, defaultValue = "1") int page,
            ModelAndView modelAndView
    ) {
        int pageSize = 10;

        Map<String, Object> params = new HashMap<>();
        params.put("searchType", searchType);
        params.put("searchWord", searchWord);
        params.put("page", page);
        params.put("pageSize", pageSize);

        PageInfo<ArticleDto> pageInfo = articleService.searchArticles(params);
        List<ReadingListResponse> articles = pageInfo.getList().stream()
                .map(ReadingListResponse::fromArticleDto)
                .collect(Collectors.toList());

        modelAndView.addObject("articles", articles);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.addObject("SearchTypes", SearchType.values());
        modelAndView.addObject("searchTypeHashtag", SearchType.HASHTAG);
        modelAndView.setViewName("community/resale-platform/reading-list");

        return modelAndView;
    }


    @GetMapping("/article/{communityNo}")
    public String getArticle(
            @PathVariable int communityNo,
            ModelMap map
    ) throws NotFoundException
    {
        GetArticleResponse article = Converter.toGetArticleResponse(articleService.getArticleComments(communityNo));
        Map<String, Object> params = new HashMap<>();
        String searchWord = "example";
        params.put("searchWord", searchWord);

        map.addAttribute("article", article);
        map.addAttribute("articleComments", article.getArticleCommentsResponse());
        map.addAttribute("totalCount", articleService.getArticleCount(params));
        map.addAttribute("searchTypeHashtag", SearchType.HASHTAG);

        return "community/resale-platform/article";
    }

    @GetMapping("/article/new")
    public String article(ModelMap modelMap)
    {
        modelMap.addAttribute("articleAction", ArticleAction.CREATE);

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
            return "redirect:";
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






