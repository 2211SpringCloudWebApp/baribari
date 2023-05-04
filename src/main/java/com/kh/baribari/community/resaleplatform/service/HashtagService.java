package com.kh.baribari.community.resaleplatform.service;

import com.kh.baribari.community.resaleplatform.domain.ArticleHashtag;
import com.kh.baribari.community.resaleplatform.repository.ArticleHashtagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class HashtagService
{


    private final ArticleHashtagRepository hashtagRepository;

    public Set<ArticleHashtag> findHashtagsByNames(Set<String> hashtagNames)
    {
        return new HashSet<>(hashtagRepository.findByHashtagNameIn(hashtagNames));
    }

    public Set<String> parseHashtagNames(String content)
    {
        if (content == null) {
            return new HashSet<>();
        }
        Pattern pattern = Pattern.compile("#[\\w가-힣]+");
        Matcher matcher = pattern.matcher(content.strip());
        Set<String> result = new HashSet<>();

        while (matcher.find()) {
            result.add(matcher.group().replace("#", ""));
        }

        return Set.copyOf(result);
    }

    public void deleteHashtagWithoutArticles(Integer communityNo)
    {
        Optional<ArticleHashtag> hashtag = hashtagRepository.findById(communityNo);
        if (hashtag.get().getArticles().isEmpty()) {
            hashtagRepository.delete(hashtag.get().getCommunityNo());
        }
    }


}
