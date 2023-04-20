package com.kh.baribari.community.resaleplatform.service;

import com.kh.baribari.community.resaleplatform.domain.ResalePost;
import com.kh.baribari.community.resaleplatform.domain.type.SearchType;
import com.kh.baribari.community.resaleplatform.repository.ResalePostRepository;
import com.kh.baribari.user.domain.User;
import com.kh.baribari.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResalePostService
{

    private final ResalePostRepository resalePostRepository;
    private final UserRepository userRepository;

    public ResalePost writeNewArticle(ResalePost resalePost, User user)
    {
        return resalePost;
    }

    public Page<ResalePost> searchArticles(Pageable pageable, SearchType searchType, String searchValue)
    {
        return Page.empty();
    }

    public ResalePost getArticle(ResalePost communityNo)
    {
        return communityNo;
    }

    public ResalePost findCommunityNo(ResalePost communityNo)
    {
        return communityNo;
    }

    public void updateArticle(ResalePost no, ResalePost resalePost)
    {
    }

    public void deleteArticle(ResalePost communityNo)
    {
    }

}


