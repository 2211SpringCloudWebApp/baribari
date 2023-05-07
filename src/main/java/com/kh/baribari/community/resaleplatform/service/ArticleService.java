package com.kh.baribari.community.resaleplatform.service;
import com.kh.baribari.community.resaleplatform.domain.Article;
import com.kh.baribari.community.resaleplatform.domain.ArticleHashtag;
import com.kh.baribari.community.resaleplatform.domain.dto.ArticleDto;
import com.kh.baribari.community.resaleplatform.domain.dto.GetArticleDto;
import com.kh.baribari.community.resaleplatform.repository.ArticleRepository;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j @Service
@RequiredArgsConstructor
public class ArticleService
{
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final HashtagService hashtagService;

    public List<ArticleDto> searchArticles(Map<String, Object> params)
    {
        return articleRepository.searchArticles(params);
    }
    public int count(Map<String, Object> params)
    {
        return articleRepository.count(params);
    }
    public GetArticleDto getArticleComments(int communityNo) throws NotFoundException
    {
        return articleRepository.findByNo(communityNo)
                .map(GetArticleDto::from)
                .orElseThrow(() -> new NotFoundException("게시글이 없습니다"));
    }
    public int countComments(int communityNo)
    {
        return articleRepository.countComments(communityNo);
    }
    public void createArticle(ArticleDto dto) {
        User user = userRepository.getUserInfo(dto.getUserDto().getUserNo());
        Set<ArticleHashtag> hashtags = renewHashtagsFromContent(dto.getCommunityContent());
        //        Set<ArticleHashtag> hashtags = hashtagService.findHashtagsByNames(hashtagService.parseHashtagNames(dto.getCommunityContent())getCommunityContent);
        Article article = dto.toEntity(user);
        article.addHashtags(hashtags);
        articleRepository.save(article);
    }
    private Set<ArticleHashtag> renewHashtagsFromContent(String content)
    {
        Set<String> hashtagNamesInContent = hashtagService.parseHashtagNames(content);
        Set<ArticleHashtag> hashtags = hashtagService.findHashtagsByNames(hashtagNamesInContent);
        Set<String> existingHashtagNames = hashtags.stream()
                .map(ArticleHashtag::getHashtagName)
                .collect(Collectors.toUnmodifiableSet());
        hashtagNamesInContent.forEach(newHashtagName -> {
            if (!existingHashtagNames.contains(newHashtagName)) {
                hashtags.add(ArticleHashtag.of(newHashtagName));
            }
        });
        return hashtags;
    }
    @Transactional(readOnly = true)
    public ArticleDto getArticle(int communityNo) throws NotFoundException
    {
        return articleRepository.findByNo(communityNo)
                .map(ArticleDto::from)
                .orElseThrow(() -> new NotFoundException("게시글이 없습니다"));
    }
    public void updateArticle(int communityNo, ArticleDto dto)
    {
        Optional<Article> article = articleRepository.findByNo(communityNo);
        User user = (User) userRepository.findByUserNo(dto.getUserDto().getUserNo());

        if (article.get().getUser().equals(user)) {
            if (dto.getCommunitySubject() != null) { article.get().setCommunitySubject(dto.getCommunitySubject()); }
            if (dto.getCommunityContent() != null) { article.get().setCommunityContent(dto.getCommunityContent()); }

            Set<Integer> hashtagIds = article.get().getHashtags().stream()
                    .map(ArticleHashtag::getCommunityNo)
                    .collect(Collectors.toUnmodifiableSet());
            article.get().clearHashtags();

            hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);

            Set<ArticleHashtag> hashtags = renewHashtagsFromContent(dto.getCommunityContent());
            article.get().addHashtags(hashtags);

            articleRepository.update(article.get());
        }
    }
    public void deleteArticle(Integer communityNo, int userNo)
    {
        Optional<Article> article = articleRepository.findByNo(communityNo);
        Set<Integer> hashtagIds = article.get().getHashtags().stream()
                .map(ArticleHashtag::getCommunityNo)
                .collect(Collectors.toUnmodifiableSet());
        articleRepository.deleteByIdAndUserAccount_UserId(communityNo, userNo);
        hashtagIds.forEach(hashtagService::deleteHashtagWithoutArticles);
    }
}

