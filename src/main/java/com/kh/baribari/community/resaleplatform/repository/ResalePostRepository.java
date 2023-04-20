package com.kh.baribari.community.resaleplatform.repository;

import com.kh.baribari.community.resaleplatform.domain.ResalePost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ResalePostRepository
{

//    한건 검색
    Object findById(Long communityNo);

//    여러건 검색
    Page<ResalePost> findAll(Pageable pageable);
    Page<ResalePost> findBySubject(String subject, Pageable pageable);
    Page<ResalePost> findByContent(String searchWord, Pageable pageable);
    Page<ResalePost> findByBoth(String searchWord, Pageable pageable);

}
