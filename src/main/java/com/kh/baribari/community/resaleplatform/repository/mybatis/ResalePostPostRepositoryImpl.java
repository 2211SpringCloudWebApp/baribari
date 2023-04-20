package com.kh.baribari.community.resaleplatform.repository.mybatis;

import com.kh.baribari.community.resaleplatform.domain.ResalePost;
import com.kh.baribari.community.resaleplatform.domain.type.SearchType;
import com.kh.baribari.community.resaleplatform.repository.ResalePostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResalePostPostRepositoryImpl implements ResalePostRepository
{

    private final ResalePostMapper resalePostMapper;

    @Override public Object findById(Long communityNo)
    {
        return null;
    }

    @Override public Page<ResalePost> findAll(Pageable pageable)
    {
        return null;
    }

    @Override public Page<ResalePost> findBySubject(String subject, Pageable pageable)
    {
        return null;
    }

    @Override public Page<ResalePost> findByContent(String searchWord, Pageable pageable)
    {
        return null;
    }

    @Override public Page<ResalePost> findByBoth(String searchWord, Pageable pageable)
    {
        return null;
    }
}
