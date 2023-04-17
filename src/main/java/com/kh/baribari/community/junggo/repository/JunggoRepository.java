package com.kh.baribari.community.junggo.repository;

import com.kh.baribari.community.junggo.domain.type.SearchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JunggoRepository implements JunggoMapper{


    @Override
    public List<JunggoRepository> searchJunggo(SearchType searchType, String searchKeyword) {
        return List.of();
    }
}
