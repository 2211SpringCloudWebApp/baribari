package com.kh.baribari.community.resaleplatform.controller;

import com.kh.baribari.common.annotation.LoginUser;
import com.kh.baribari.community.resaleplatform.domain.ResalePost;
import com.kh.baribari.community.resaleplatform.domain.type.ArticleAction;
import com.kh.baribari.community.resaleplatform.domain.type.SearchType;
import com.kh.baribari.community.resaleplatform.service.PaginationService;
import com.kh.baribari.community.resaleplatform.service.ResalePostService;
import com.kh.baribari.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("resale")
public class ResalePostController
{

  private final ResalePostService resalePostService;
  private final PaginationService paginationService;
  // 🔅 목록 페이지
  @GetMapping
  public String getReadingList(
          @RequestParam(required = false) SearchType searchType,
          @RequestParam(required = false) String searchValue,
          @PageableDefault(size = 5, sort = "communityDate", direction = Sort.Direction.DESC) Pageable pageable,
          ModelMap modelMap
                              )
  {

    Page<ResalePost> article = resalePostService.searchArticles(pageable, searchType, searchValue);
    List<Integer> barNum = paginationService.getPaginationBarNumbers(pageable.getPageNumber(), article.getTotalPages());

    modelMap.addAttribute("article", article);
    modelMap.addAttribute("barNum", barNum);
    modelMap.addAttribute("searchType", SearchType.values());

    return "community/resale-platform/reading-list";
  }

  //🔅 새글쓰기  - TODO : 로그인만
  @GetMapping("/article")
  public String article(
          @LoginUser User user,
          ModelMap modelMap
                       )
  {
    modelMap.addAttribute("articleActions", ArticleAction.CREATE);
    return "community/resale-platform/article";
  }

  @PostMapping("/article")
  public String writeArticle(
          @Valid ResalePost resalePost,
          @LoginUser User user
                            )
  {
    resalePostService.writeNewArticle(resalePost, user);

    return "redirect:community/resale-platform/reading-list";
  }

  // 🔅상세페이지 -- TODO: 로그인
  @GetMapping("/article/{communityNo}")
  public String getArticle(
          @PathVariable ResalePost communityNo,
          @LoginUser User user,
          Model Model
                          ) {
    Model.addAttribute(communityNo);
    Model.addAttribute(user);

    return "community/resale-platform/article";
  }

  //🔅  상세 수정  -- TODO : 로그인
  @GetMapping("/article/{communityNo}/update")
  public String updateArticle(
          @PathVariable ResalePost communityNo,
          @LoginUser User user,
          ModelMap modelMap
                             )
  {
    ResalePost article = resalePostService.getArticle(communityNo);
    modelMap.addAttribute("article", article);
    modelMap.addAttribute("articleAction", ArticleAction.UPDATE);
    modelMap.addAttribute("user", user);
    return "community/resale-platform/article";
  }

  @PutMapping("/article/{communityNo}/update")
  public String updateArticlePost(
          @PathVariable ResalePost communityNo,
          @Valid ResalePost resalePost,
          @LoginUser User user
                                 )
  {
    ResalePost no = resalePostService.findCommunityNo(communityNo);

    resalePostService.updateArticle(no, resalePost);


    return "redirect:/article/{communityNo}";
  }

  //🔅 상세 삭제  -- TODO : 로그인
  @DeleteMapping("/article/{communityNo}/delete")
  public String deleteArticle(
          @PathVariable ResalePost communityNo,
          @LoginUser User user
                             )
  {
    resalePostService.deleteArticle(communityNo);

    return "redirect:";
  }

}